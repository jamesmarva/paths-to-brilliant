package second;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-18 01:09
 **/
public class VersionTwoTomcat {

    private int port = 8080;

    private Map<String, SecondRootServlet> urlMapServlet = new ConcurrentHashMap<>();

    /**
     * 表示的就是web.xml 中的那个配置
     */
    private Properties properties = new Properties();


    private void init() {
        try {
            String propertiesPath = this.getClass().getResource("/").getPath();
            FileInputStream fileInputStream = new FileInputStream(propertiesPath + "web.properties");
            properties.load(fileInputStream);
            for (Object k : properties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String url = properties.getProperty(key);
                    String servletNamePrefix  = key.replace("\\.url$", "");
                    String className = (String) properties.get(servletNamePrefix + ".className");
                    SecondRootServlet servletInstance = (SecondRootServlet) Class.forName(className)
                            .getDeclaredConstructor().newInstance();
                    urlMapServlet.put(url, servletInstance);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
