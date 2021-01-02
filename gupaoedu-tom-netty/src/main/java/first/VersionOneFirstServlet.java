package first;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-17 23:08
 **/
public class VersionOneFirstServlet extends FirstRootServlet {

    @Override
    public void doGet(FirstRequest request, FirstResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(FirstRequest request, FirstResponse response) throws Exception {
        response.write("version one");
    }
}
    