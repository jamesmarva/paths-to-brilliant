package reverse;

import io.undertow.client.UndertowClient;
import io.undertow.server.handlers.proxy.ConnectionPoolErrorHandler;
import io.undertow.server.handlers.proxy.ConnectionPoolManager;
import io.undertow.server.handlers.proxy.ProxyConnectionPool;
import org.xnio.OptionMap;
import org.xnio.ssl.XnioSsl;

import java.net.InetSocketAddress;
import java.net.URI;

/**
 *
 * @author 王涵威
 * @date 21.1.1 10:11
 */
public final class Host extends ConnectionPoolErrorHandler.SimpleConnectionPoolErrorHandler implements ConnectionPoolManager {

    private int problemServerRetry = 10 ;

    private int connectionsPerThread = 20;

    private volatile int maxQueueSize = 0;

    private volatile int softMaxConnectionsPerThread = 5;

    private volatile int ttl = -1;

    private final ProxyConnectionPool connectionPool;

    private final String jvmRoute;

    private final URI uri;

    private final XnioSsl ssl;

    private UndertowClient client;

    public Host(URI uri) {
        this(uri, UndertowClient.getInstance());
    }

    public Host(URI uri, UndertowClient client) {
        this(null, null, uri, null, OptionMap.EMPTY, client);
    }

    private Host(String jvmRoute, InetSocketAddress bindAddress, URI uri, XnioSsl ssl, OptionMap options, UndertowClient client) {
        this.client = client;
        this.connectionPool = new ProxyConnectionPool(this, bindAddress, uri, ssl, client, options);
        this.jvmRoute = jvmRoute;
        this.uri = uri;
        this.ssl = ssl;
    }

    public void setProblemServerRetry(int problemServerRetry) {
        this.problemServerRetry = problemServerRetry;
    }

    public void setConnectionsPerThread(int connectionsPerThread) {
        this.connectionsPerThread = connectionsPerThread;
    }

    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public void setSoftMaxConnectionsPerThread(int softMaxConnectionsPerThread) {
        this.softMaxConnectionsPerThread = softMaxConnectionsPerThread;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public ProxyConnectionPool getConnectionPool() {
        return connectionPool;
    }

    @Override
    public int getProblemServerRetry() {
        return problemServerRetry;
    }

    @Override
    public int getMaxConnections() {
        return connectionsPerThread;
    }

    @Override
    public int getMaxCachedConnections() {
        return connectionsPerThread;
    }

    @Override
    public int getSMaxConnections() {
        return softMaxConnectionsPerThread;
    }

    @Override
    public long getTtl() {
        return ttl;
    }

    @Override
    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public URI getUri() {
        return uri;
    }

//    public void closeCurrentConnections() {
//        connectionPool.closeCurrentConnections();
//    }
}
