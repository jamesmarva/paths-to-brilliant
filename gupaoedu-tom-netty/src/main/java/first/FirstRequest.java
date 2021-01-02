package first;

import java.io.InputStream;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 22:52
 **/
public class FirstRequest {

    private String method;
    private String url;

    public FirstRequest(InputStream in) {
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = in.read(buffer)) > 0) {

            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }
}
