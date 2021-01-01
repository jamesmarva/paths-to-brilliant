package guavademo.limiter;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import threads.MyThreadPoolExecutor;
import threads.tomcat.ThreadPoolExecutorTom;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.1 15:40
 */
public class RateLimiterDemo {
    public static void main(String[] args) {
        ThreadFactory tf = new ThreadFactoryBuilder()
                .setNameFormat("RateLimiter-Demo").build();
        ThreadPoolExecutor tpe = MyThreadPoolExecutor.buildExecutor(1000, tf);
        RateLimiter limiter = RateLimiter.create(2.0);

        final long[] prev = {System.currentTimeMillis()};
        for (int i=0; i<20; i++){
            //限流器限流
            limiter.acquire(); //提交任务异步执行
            tpe.execute(()-> {
                long cur=System.currentTimeMillis(); //打印时间间隔：毫秒
                System.out.println(cur- prev[0]);
                prev[0] = cur;
            });
        }
        tpe.shutdown();
    }
}
