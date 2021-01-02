package ch02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-11-07 01:00
 **/
public class NioServer {

    public static void main(String[] args) throws IOException {

        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                // channel listen port 8000
                listenerChannel.socket().bind(new InetSocketAddress(8000));
                listenerChannel.configureBlocking(false);
                // channel register in server selector
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                while (true) {
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            try {
                                SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();

                                clientChannel.configureBlocking(false);
                                // channel register in client
                                clientChannel.register(clientSelector, SelectionKey.OP_READ);
                            } finally {
                                keyIterator.remove();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                while (true) {
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = set.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey keyTmp = iterator.next();
                            if (keyTmp.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) keyTmp.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println("msg: "
                                            + Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                } finally {
                                    iterator.remove();
                                    keyTmp.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
