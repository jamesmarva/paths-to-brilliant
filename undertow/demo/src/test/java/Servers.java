import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.4 21:24
 */
public class Servers {

    public static void main(String[] args) {
        // set up the undertow server1
        final Undertow s1 = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders()
                                .put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("1111111111111111111");
                    }
                })
                .build();

        // set up the undertow server1
        final Undertow s2 = Undertow.builder()
                .addHttpListener(8082, "localhost")
                .setHandler(new HttpHandler() {

                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders()
                                .put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("2-2-2-2-2-2-2-2-2-2-2--2");
                    }
                }).build();

        // set up the undertow server1
        final Undertow s3 = Undertow.builder()
                .addHttpListener(8083, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders()
                                .put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("3333333333333333333");

                    }
                })
                .build();

        s1.start();
        s2.start();
        s3.start();
    }
}
