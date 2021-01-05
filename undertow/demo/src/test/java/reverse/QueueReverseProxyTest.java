package reverse;

import io.undertow.Undertow;
import io.undertow.server.handlers.ResponseCodeHandler;
import io.undertow.server.handlers.proxy.ProxyHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class QueueReverseProxyTest {

    public static void main(String[] args) throws URISyntaxException {
        Host host = new Host(new URI("http://localhost:8081"));
        BlockingQueue<Host> hosts = new LinkedBlockingQueue<>();
        hosts.add(host);

        Host defaultHost = new Host(new URI("http://localhost:8082"));
        QueueReverseProxy proxy = new QueueReverseProxy(hosts, defaultHost);
        Undertow reverseProxyServer = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setIoThreads(4)
                .setHandler(new ProxyHandler(proxy, 30000, ResponseCodeHandler.HANDLE_404))
                .build();
        reverseProxyServer.start();
    }

}