package ch02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * AIO 创建 TimeServer
 * code 11, code 12, code 13, code 14
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-07 21:15
 **/
public class Cod11 {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new Thread(new AsyncTimeServerHandler(port), "AIO").start();
    }
}

class AsyncTimeServerHandler implements Runnable {

    private int port;

    public CountDownLatch latch;

    private AsynchronousSocketChannel asyncSocketChannel;

    public AsynchronousServerSocketChannel asyncSrvSockChannel;

    public AsyncTimeServerHandler(int port) throws Exception {
        this.port = port;
        asyncSrvSockChannel = AsynchronousServerSocketChannel.open();
        asyncSrvSockChannel.bind(new InetSocketAddress(this.port));
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (Exception e) {

        }
    }

    private void doAccept() {
        asyncSrvSockChannel.accept(this, new AcceptCompletionHandler());
    }
}

class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        attachment.asyncSrvSockChannel.accept(attachment, this);
        ByteBuffer buffer  = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc .printStackTrace();
        attachment.latch.countDown();
    }
}

class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel asyncSocketChannel;

    public ReadCompletionHandler(AsynchronousSocketChannel c) {
        if (c != null) {
            this.asyncSocketChannel = c;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, StandardCharsets.UTF_8);
            System.out.println("receive order: " + req);
            String crtTime = "QUERY".equalsIgnoreCase(req) ? new Date(System.currentTimeMillis()).toString() : "BAD";
            doWrite(crtTime);
        } catch (Exception e) {

        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.asyncSocketChannel.close();
        } catch (Exception e) {

        }
    }


    private void doWrite(String currentTime) {
        if (currentTime != null && currentTime.trim().length() > 0) {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            asyncSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer bfr) {
                    if (bfr.hasRemaining()) {
                        asyncSocketChannel.write(bfr, bfr, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asyncSocketChannel.close();
                    } catch (Exception e) {

                    }
                }
            });
        }
    }
}
