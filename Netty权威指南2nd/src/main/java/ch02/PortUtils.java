package ch02;

import java.util.Objects;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2021-03-03 01:17
 **/
public class PortUtils {

    public static int port(String[] args) {
        int port = 8080;
        if (Objects.nonNull(args) && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);

            } catch (Exception e) {

            }
        }
        return port;

    }

}
