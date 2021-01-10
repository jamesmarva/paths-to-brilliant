package second;

import first.FirstRequest;
import first.FirstResponse;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 23:35
 **/
public abstract class SecondRootServlet {

    public void service(FirstRequest request, FirstResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(FirstRequest request, FirstResponse response) throws Exception;

    public abstract void doPost(FirstRequest request, FirstResponse response) throws Exception;

}
