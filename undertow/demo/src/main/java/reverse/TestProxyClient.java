package reverse;

import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.proxy.ProxyCallback;
import io.undertow.server.handlers.proxy.ProxyClient;
import io.undertow.server.handlers.proxy.ProxyConnection;

import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.1 9:52
 */
public class TestProxyClient implements ProxyClient {



    private HostQueue hostQueue;

    public TestProxyClient(HostQueue hostQueue) {
        this.hostQueue = hostQueue;
    }



    @Override
    public ProxyTarget findTarget(HttpServerExchange exchange) {
        return null;
    }

    @Override
    public void getConnection(ProxyTarget target, HttpServerExchange exchange, ProxyCallback<ProxyConnection> callback, long timeout, TimeUnit timeUnit) {

    }


}
