package ch16.demo4;

import ch16.sec02.Level;
import ch16.sec02.Request;
import ch16.sec02.Response;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-06 12:58
 **/
public class ConcreteHandler1 implements Handler {


    private Handler next;

    private Level level;

    ConcreteHandler1(){
        this.level = new Level();
    }


    @Override
    public void setNextHandler(Handler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public Handler getNextHandler() {
        return this.next;
    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public Response coreHandle(Request request) {
        return null;
    }
}
