package reverse.proxy;

import io.undertow.Undertow;
import io.undertow.server.handlers.proxy.LoadBalancingProxyClient;
import io.undertow.server.handlers.proxy.ProxyHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-01-01 00:41
 **/
public class BalancingProxyDemo {

    public static void main(String[] args) {
        LoadBalancingProxyClient loadBalancer = null;
        try {
            loadBalancer = new LoadBalancingProxyClient()
                    .addHost(new URI("http://localhost:8081"))
                    .addHost(new URI("http://localhost:8082"))
                    .addHost(new URI("http://localhost:8083"))
                    .setConnectionsPerThread(20);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Undertow reverseProxy = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setIoThreads(4)
                .setHandler(ProxyHandler.builder().setProxyClient(loadBalancer).setMaxRequestTime( 30000).build())
                .build();
        reverseProxy.start();
    }
}
