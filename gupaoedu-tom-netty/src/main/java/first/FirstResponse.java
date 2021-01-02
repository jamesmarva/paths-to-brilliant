package first;

import java.io.OutputStream;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 22:52
 **/
public class FirstResponse {

    private OutputStream out;

    public FirstResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK\n")
                .append("Content-type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(builder.toString().getBytes());
    }

}
