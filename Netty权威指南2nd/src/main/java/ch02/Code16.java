package ch02;

import javax.websocket.RemoteEndpoint;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * AIO client
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-07 23:10
 **/
public class Code16 {

}

class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

    private AsynchronousSocketChannel asynchronousSocketChannel;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncTimeClientHandler(String host, int port) throws Exception {
        this.host = host;
        this.port = port;
        asynchronousSocketChannel = AsynchronousSocketChannel.open();
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
        } catch (Exception e) {

        }
    }

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        byte[] req = "QUERY".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buf) {
                if (buf.hasRemaining()) {
                    asynchronousSocketChannel.write(buf, buf, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body;
                            try {
                                body = new String(bytes, StandardCharsets.UTF_8);
                                System.out.println("body is: " + body);
                                latch.countDown();
                            } catch (Exception e) {

                            }

                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    asynchronousSocketChannel.close();
                    latch.countDown();
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        try {
            asynchronousSocketChannel.close();
            latch.countDown();
        } catch (Exception e) {

        }
    }
}

