package first;

/**
 * 最简单的 Servlet <br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 22:51
 **/
public abstract class FirstRootServlet {


    public void service(FirstRequest request, FirstResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {

        } else {

        }
    }

    public abstract void doGet(FirstRequest request, FirstResponse response) throws Exception;

    public abstract void doPost(FirstRequest request, FirstResponse response) throws Exception;
}
