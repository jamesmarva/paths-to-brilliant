package first;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-18 00:49
 **/
public class VersionOneSecondServlet extends FirstRootServlet {
    @Override
    public void doGet(FirstRequest request, FirstResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(FirstRequest request, FirstResponse response) throws Exception {
        response.write("VersionOneSecondServlet");
    }
}
