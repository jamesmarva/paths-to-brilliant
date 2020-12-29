package ch16.sec02;

/**
 * the template handler
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-06 12:00
 **/
public abstract class Handler {

    private Handler nextHandler;

    public final Response handleMessage(Request request) {
        Response response = null;
        if (this.getHandlerLevel().equals(request.getRequestLevel())) {
            response = echo(request);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.handleMessage(request);
            } else {
                // no handler handle it,
            }
        }
        return response;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler =nextHandler;
    }

    /**
     * get the level of handler.
     * @return
     */
    protected abstract Level getHandlerLevel();

    /**
     * only deal the request.
     * @param request
     * @return
     */
    protected abstract Response echo(Request request);
}
