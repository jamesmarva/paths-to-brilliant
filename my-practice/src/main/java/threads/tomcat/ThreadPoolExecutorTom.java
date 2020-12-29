package threads.tomcat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Tomcat 中的线程池的实现
 *
 * 1
 * 2
 * 3
 *
 *
 * @author 王涵威
 * @date 2020/6/29 12:06
 */
public class ThreadPoolExecutorTom  extends ThreadPoolExecutor {


    public ThreadPoolExecutorTom(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


}
