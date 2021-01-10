package core;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.31 12:21
 */
public class HelloWorldServer {

    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(9090, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("hello brilliant wanghanwei");
                    }
                })
                .build();
        server.start();
    }
}
