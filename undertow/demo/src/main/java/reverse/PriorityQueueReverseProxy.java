package reverse;

import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.proxy.ProxyCallback;
import io.undertow.server.handlers.proxy.ProxyClient;
import io.undertow.server.handlers.proxy.ProxyConnection;
import lombok.extern.slf4j.Slf4j;
import reverse.entity.PriorityHostEntity;
import reverse.limiter.SmoothBurstyRateLimiter;

import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//import java.util.PriorityQueue;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;

    /**
     *
     *
     * @author 王涵威
     * @date 21.1.4 11:52
     */
@Slf4j
public class PriorityQueueReverseProxy implements ProxyClient  {

    private final Host defaultHost;

    private static final ProxyTarget PROXY_TARGET = new ProxyTarget() {};

//    private final ReentrantReadWriteLock R_W_LOCK = new ReentrantReadWriteLock();

//    private final ReentrantReadWriteLock.ReadLock READ_LOCK = R_W_LOCK.readLock();

//    private final ReentrantReadWriteLock.WriteLock WRITE_LOCK = R_W_LOCK.writeLock();

    private final PriorityBlockingQueue<PriorityHostEntity> priorityQueue;

    private final ReentrantLock LOCK = new ReentrantLock();

    public PriorityQueueReverseProxy(PriorityBlockingQueue<PriorityHostEntity> queue, Host defaultHost) {
        this.priorityQueue = queue;
        this.defaultHost = defaultHost;
    }

    @Override
    public ProxyTarget findTarget(HttpServerExchange exchange) {
        return PROXY_TARGET;
    }

    @Override
    public void getConnection(ProxyTarget target,
                              HttpServerExchange exchange,
                              ProxyCallback<ProxyConnection> callback,
                              long timeout,
                              TimeUnit timeUnit) {
        Host h = null;
        PriorityHostEntity entity = null;
        entity = priorityQueue.poll();
        SmoothBurstyRateLimiter limiter = Objects.requireNonNull(entity).getLimiter();
//        log.info("limitor: " + limiter.queryEarliestAvailable(0));
        if (limiter.tryAcquire(1, 200L, TimeUnit.MICROSECONDS)) {
            h = entity.getHost();
            priorityQueue.offer(entity);
            Objects.requireNonNull(h).getConnectionPool().connect(target,
                    exchange,
                    callback,
                    timeout,
                    timeUnit,
                    false);
        } else {
            System.out.println("please add server");
            priorityQueue.offer(entity);
            defaultHost.getConnectionPool().connect(target, exchange,callback, timeout, timeUnit, false);
        }
    }

    private class ExchangeEntity {

    }
}
