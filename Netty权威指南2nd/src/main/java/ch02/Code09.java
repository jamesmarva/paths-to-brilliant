package ch02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.5 13:54
 */
public class Code09 {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length >0) {
            port = Integer.parseInt(args[0]);
        }


    }
}

class TimeClientHandler implements Runnable {

    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public TimeClientHandler(String host, int port) throws  Exception {
        this.host = host;
        this.port = port;
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        stop = false;
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {

        }


    }

    private void doConnect() throws Exception  {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite();
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite() throws Exception {
        byte[] req = "QUERY".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("send order to server.");
        }
    }
}
