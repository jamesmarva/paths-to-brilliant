package ch22;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.29 12:03
 */
public class ThreadPoolExecutor0 {
    
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10,
                100,
                120,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(Integer.MAX_VALUE),
                new CustomThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}

class CustomThreadFactory implements ThreadFactory {


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread("Thread Pool Demo 0");
        return thread;
    }
}
