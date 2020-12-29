package ch16.demo3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-06 13:36
 **/
public class HandlerChain {

    private final static Map<Level, Handler> chain = new ConcurrentHashMap<>();

    public static void put(Level level, Handler handler) {
        chain.put(level, handler);
    }
}
