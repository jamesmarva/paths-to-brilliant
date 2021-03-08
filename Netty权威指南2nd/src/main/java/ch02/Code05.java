package ch02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 伪异步IO
 * 缺点就是，从每个socket读取出来的流还是同步阻塞的，如果遇到了网络比较差的情况，那么就会导致线程池的工作线程被占满，
 * 后续的accptor接受进来的socket都会被放在队列里，直到队列被填满，然后后续的进来的socket就会被拒绝策略拒绝，直接导致错误，
 * 直接放回服务端错误。
 *
 * 这个就是伪异步IO的弊端
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-03 01:16
 **/
public class Code05 {

    public static void main(String[] args) {
        int port = PortUtils.port(args);
        try (ServerSocket serverSocket = new ServerSocket()) {
            System.out.println("port: " + port);
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    2 * Runtime.getRuntime().availableProcessors() + 1,
                    30L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1000)
            );
            while (true) {
                // acceptor thread
                Socket socket = serverSocket.accept();
                tpe.execute(new Code01.ServerHandler(socket));
            }

        } catch (IOException e) {

        }
    }
}
