package reverse.proxy;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-01-01 00:41
 **/
public class ThreeServerDemo {

    public static void main(String[] args) {
        final Undertow server1 = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Server1");
                    }
                })
                .build();

        server1.start();

        final Undertow server2 = Undertow.builder()
                .addHttpListener(8082, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Server2");
                    }
                })
                .build();
        server2.start();

        final Undertow server3 = Undertow.builder()
                .addHttpListener(8083, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Server3");
                    }
                })
                .build();

        server3.start();
    }
}
