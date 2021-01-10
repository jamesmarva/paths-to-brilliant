package reverse;

import guava.SleepingStopwatch;
import guava.SmoothBurstyRateLimiter;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.proxy.ProxyCallback;
import io.undertow.server.handlers.proxy.ProxyClient;
import io.undertow.server.handlers.proxy.ProxyConnection;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.4 20:06
 */
public class QueueReverseProxy implements ProxyClient {

    private final static ProxyTarget PROXY_TARGET = new ProxyTarget() {};

    private BlockingQueue<Host> hosts;

    private final static SmoothBurstyRateLimiter LIMITER = new SmoothBurstyRateLimiter(SleepingStopwatch.createFromSystemTimer());

    static {
        LIMITER.setRate(1.0);
    }
//    private final static RateLimiter LIMITER = RateLimiter.create(1.0);

    private final Host defaultHost;

    public QueueReverseProxy(BlockingQueue<Host> h, Host defaultHost) {
        this.defaultHost = defaultHost;
        this.hosts = h;
    }

    @Override
    public ProxyTarget findTarget(HttpServerExchange exchange) {
        return PROXY_TARGET;
    }

    @Override
    public void getConnection(ProxyTarget target, HttpServerExchange exchange,
                              ProxyCallback<ProxyConnection> callback, long timeout, TimeUnit timeUnit) {
        if (LIMITER.tryAcquire(1, 100L, TimeUnit.MILLISECONDS)) {
            Host tmpHost = hosts.poll();
            System.out.println(LIMITER);
            Objects.requireNonNull(tmpHost).getConnectionPool().connect(target, exchange, callback, timeout, timeUnit, false);
            hosts.offer(tmpHost);
        } else {
            defaultHost.getConnectionPool().connect(target, exchange, callback, timeout, timeUnit, false);
        }
    }
}
