package ch16.demo4;

import ch16.sec02.Level;
import ch16.sec02.Request;
import ch16.sec02.Response;

import java.util.Objects;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-06 12:34
 **/
public interface Handler {

    void setNextHandler(Handler nextHander);

    Handler getNextHandler();

    /**
     * 当前节点的处理级别
     * @return
     */
    Level getLevel();

    /**
     * 节点处理请求的核心
     * @param request
     * @return
     */
    Response coreHandle(Request request);

    /**
     *
     * @param request request be handled
     * @return
     */
    default Response handleMessage(Request request) {
        Response response = null;
        if (getLevel() == request.getRequestLevel()) {
            return coreHandle(request);
        } else {
            if (Objects.nonNull(getNextHandler())) {
                return getNextHandler().handleMessage(request);
            } else {
//                do nothing
            }
        }
        return response;
    }
}
