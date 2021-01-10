package reverse;

import io.undertow.Undertow;
import io.undertow.server.handlers.proxy.LoadBalancingProxyClient;
import io.undertow.server.handlers.proxy.ProxyHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 *
 *
 * @author 王涵威
 * @date 21.1.1 8:56
 */
public class ReverseProxyServer {

    public static void main(String[] args) throws URISyntaxException {
        LoadBalancingProxyClient loadBalancingProxyClient  = new LoadBalancingProxyClient()
                .addHost(new URI("http://localhost:8081"))
                .addHost(new URI("http://localhost:8082"))
                .addHost(new URI("http://localhost:8083"))
                .setConnectionsPerThread(20);
        Undertow proxyServer = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setIoThreads(4)
                .setHandler(ProxyHandler.builder()
                        .setProxyClient(loadBalancingProxyClient)
                        .setMaxRequestTime(30000)
                        .build())
                .build();
        proxyServer.start();


    }
}
