package ch02;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-11-06 02:07
 **/
public class IoClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8000)) {
//            socket.bind();
            String msg;
//            while (true) {
//                socket.connect(new InetSocketAddress("127.0.0.1", 8000));
                msg = "hello james" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                socket.getOutputStream().write(msg.getBytes());
//                socket.close();
                Thread.sleep(1000L);
//            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
