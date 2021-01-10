package threads;


import java.sql.Time;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.1 15:45
 */
public class MyThreadPoolExecutor {
    public static ThreadPoolExecutor buildExecutor(int queueSize, ThreadFactory tf) {
        int numOfProcessor = Runtime.getRuntime().availableProcessors();

        return new ThreadPoolExecutor(numOfProcessor,
                2 * numOfProcessor + 1,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(queueSize),
                tf);
    }
}
