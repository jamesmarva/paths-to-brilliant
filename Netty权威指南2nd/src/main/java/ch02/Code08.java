package ch02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-04 01:20
 **/
public class Code08 {

    public static void main(String[] args) {
        int port = PortUtils.port(args);

    }
}



class NioServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel srvChannel;

    private volatile boolean stop;

    public NioServer(int port) {
        try {
            // 1 open channel,
            srvChannel  = ServerSocketChannel.open();

            // 2 bind port and config no blocking
            srvChannel.socket().bind(new InetSocketAddress(port), 1024);
            srvChannel.configureBlocking(false);

            // 3 open the selector and register channel to selector
            selector = Selector.open();

            // 4 make channel register selector, and listen OP_ACCEPT event.
            srvChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {

        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {

        // 5 多路复用器在线程run方法的无限轮询准备就绪的Key
        while (!stop) {
            try {
//                selector.select(1000);
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (Objects.nonNull(key)) {
                            key.cancel();
                            if (Objects.nonNull(key.channel())) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private void handleInput(SelectionKey key) throws  Exception {
        if (key.isValid()) {
            // 如果状态是acceptable，那么就转换为 ServerSocketChannel
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
//                6 多路复用器监听到有新的客户端接入 ，就处理新的请求，完成TCP三次握手。
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // add the new connection to the selector.
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                // read the data
                SocketChannel sc = (SocketChannel) key.channel();
                // 这里仅仅假设
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytesNum = sc.read(readBuffer);
                if (readBytesNum > 0) {
//                   idx: 0; count: idx before
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("body: " + body);

                    String currentTime = "QUERY".equals(body) ?
                            new Date(System.currentTimeMillis()).toString() : "Bad order";
                    doWrite(sc, currentTime);
                } else if (readBytesNum < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }

        if (Objects.nonNull(selector)) {
            try {
                selector.close();

            } catch (Exception e) {

            }
        }
    }

    /**
     *
     * @param channel
     * @param resp
     * @throws Exception
     */
    private void doWrite(SocketChannel channel, String resp) throws Exception {
        if (Objects.nonNull(resp) && resp.trim().length() > 0) {
            byte[] bytes = resp.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            // write byte to buffer.
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}