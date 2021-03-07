package ch02;

import javax.websocket.RemoteEndpoint;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-07 23:10
 **/
public class Code16 {

}

class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

    private AsynchronousSocketChannel client;

    private String host;

    private int port;

    private CountDownLatch latch;

    public AsyncTimeClientHandler(String host, int port) throws Exception {
        this.host = host;
        this.port = port;
        client = AsynchronousSocketChannel.open();
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
        } catch (Exception e) {

        }
    }

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {

    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {

    }
}
