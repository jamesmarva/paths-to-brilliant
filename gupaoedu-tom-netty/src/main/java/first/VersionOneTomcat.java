package first;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 23:12
 **/
public class VersionOneTomcat {

    private int port = 8080;

    private ServerSocket serverSocket;

    private Map<String, FirstRootServlet> urlMapServet = new ConcurrentHashMap<>();

    private Properties webXml = new Properties();

    private void init() {
        try {
            String propertiesPath = this.getClass().getResource("/").getPath();
            FileInputStream fileInputStream = new FileInputStream(propertiesPath + "web.properties");
            webXml.load(fileInputStream);
            for (Object k : webXml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String url = webXml.getProperty(key);
                    String servletNamePrefix  = key.replace("\\.url$", "");
                    String className = (String) webXml.get(servletNamePrefix + ".className");
                    FirstRootServlet servletInstance = (FirstRootServlet) Class.forName(className)
                                                                        .getDeclaredConstructor().newInstance();
                    urlMapServet.put(url, servletInstance);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Tomcat start... port: " + this.port);
            while (true) {
                Socket client = serverSocket.accept();
                process(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void process(Socket client) throws Exception {
        InputStream in  = client.getInputStream();
        OutputStream out = client.getOutputStream();

        FirstRequest request = new FirstRequest(in);
        FirstResponse response = new FirstResponse(out);

        String url = request.getUrl();

        if (urlMapServet.containsKey(url)) {
            urlMapServet.get(url).service(request, response);
        } else {
            response.write("404 - not found.");
        }
        out.flush();
        out.close();

        in.close();
        client.close();
    }


    public static void main(String[] args) {
//        new VersionOneTomcat().start();

        String ss = "servlet.one.url";
        System.out.println(ss.replaceAll("\\.url$", ""));
    }
}
