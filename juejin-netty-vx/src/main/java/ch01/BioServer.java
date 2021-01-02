package ch01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-11-06 01:52
 **/
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程
        while (true) {
            try {
                // (1) 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();

                // (2) 每一个新的连接都创建一个线程，负责读取数据
                new Thread(() -> {
                    try {
                        int len;
                        byte[] data = new byte[8096];
                        InputStream inputStream = socket.getInputStream();
                        StringBuilder sb = new StringBuilder();
                        // (3) 按字节流方式读取数据
                        // 明白 read 这个方法什么时候会返回 -1 很关键
                        while ((len = inputStream.read(data)) != -1) {
//                            System.out.println(new String(data, 0, len));
                            sb.append(new String(data, 0, len));
                        }
                        System.out.println(sb.toString());
                    } catch (IOException e) {
                    }
                }).start();

            } catch (IOException e) {
            }

        }
    }
}
