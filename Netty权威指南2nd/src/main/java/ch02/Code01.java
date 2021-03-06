package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 *
 * @author 王涵威
 * @date 21.2.23 10:51
 */
public class Code01 {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length  > 0) {
            try {
                port = Integer.parseInt(args[0]);

            } catch (Exception e) {

            }
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public final static class ServerHandler implements Runnable {

        private Socket socket;

        public ServerHandler(Socket s) {
            this.socket = s;
        }
        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String curTime = null;
                String body = null;
                while (true) {
                    body = in.readLine();
                    if (body == null) {
                        break;
                    }
                    System.out.println("body: " + body);
                    curTime = "time".equals(body) ? new Date(System.currentTimeMillis()).toString() : "bad";
                    out.println(curTime);
                }
            } catch (IOException ioe) {

            }
        }
    }
}
