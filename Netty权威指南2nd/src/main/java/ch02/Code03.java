package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.2.23 11:12
 */
public class Code03 {

    public static void main(String[] args) {
        int port = 8080;
        if (Objects.nonNull(args) && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ig) { }
        }

        try (Socket sck = new Socket("localhost", port);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(sck.getInputStream()));
             PrintWriter out = new PrintWriter(sck.getOutputStream(), true);
        ) {
            out.println("time");
            String resp = in.readLine();
            System.out.println("now :" + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
