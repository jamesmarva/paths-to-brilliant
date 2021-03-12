package ch02;

import java.nio.channels.CompletionHandler;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-07 23:10
 **/
public class Code15 {

    public static void main(String[] args) throws Exception {
        int port = PortUtils.port(args);
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port),
                "AIO-AsyncTimeClientHandler-001").start();
    }
}
