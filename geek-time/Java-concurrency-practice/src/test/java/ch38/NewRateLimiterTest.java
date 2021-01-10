package ch38;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import threads.MyThreadPoolExecutor;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.*;

class NewRateLimiterTest {

    @Test
    void testAcquire() {
    }

    @Test
    void tryAcquire() {
    }

    @Test
    void acquire() {
        ThreadFactory tf = new ThreadFactoryBuilder()
                .setNameFormat("RateLimiter-Demo").build();
        ThreadPoolExecutor tpe = MyThreadPoolExecutor.buildExecutor(1000, tf);

        NewRateLimiter limiter = NewRateLimiter.create(2.0);

        long prev = System.currentTimeMillis();
        for (int i=0; i<20; i++){
            //限流器限流
            limiter.acquire(); //提交任务异步执行
            System.out.println(limiter.queryEarliestAvailable(0L));
            long cur=System.currentTimeMillis(); //打印时间间隔：毫秒
            System.out.println( (cur- prev));
            prev = cur;
        }
        tpe.shutdown();
    }

    @Test
    void testRandomAcquire() {
        System.out.println("asdfasdfasfasfdasfdsaf");
//        ThreadFactory tf = new ThreadFactoryBuilder()
//                .setNameFormat("RateLimiter-Demo").build();
//        ThreadPoolExecutor tpe = MyThreadPoolExecutor.buildExecutor(1000, tf);
        ExecutorService es =  Executors.newCachedThreadPool();
        NewRateLimiter l1 = NewRateLimiter.create(4.0);
        Random r  = new Random(47);
        for (int i=0; i<20; i++){
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(600));
            } catch (InterruptedException ig) {
            }
            //限流器限流
            l1.acquire(); //提交任务异步执行
            System.out.println("l1: " + l1.queryEarliestAvailable(0L));
//                long cur=System.currentTimeMillis(); //打印时间间隔：毫秒
//                System.out.println(l1.queryEarliestAvailable(0));
//                prev = cur;
        }
//        es.execute(() -> {
//            Random r  = new Random(47);
//            for (int i=0; i<20; i++){
//                try {
//                    TimeUnit.MILLISECONDS.sleep(r.nextInt(300));
//                } catch (InterruptedException ig) {
//                }
//                //限流器限流
//                l1.acquire(); //提交任务异步执行
//                System.out.println("l1: " + l1.queryEarliestAvailable(0L));
////                long cur=System.currentTimeMillis(); //打印时间间隔：毫秒
////                System.out.println(l1.queryEarliestAvailable(0));
////                prev = cur;
//            }
//        });

//        NewRateLimiter l2 = NewRateLimiter.create(4.0);
//        es.execute(() -> {
//            Random r  = new Random(99);
//            for (int i=0; i<20; i++){
//                try {
//                    TimeUnit.MILLISECONDS.sleep(r.nextInt(300));
//                } catch (InterruptedException ig) {
//                }
//                //限流器限流
//                l2.acquire(); //提交任务异步执行
//                System.out.println("l2: " + l2.queryEarliestAvailable(0L));
////                long cur=System.currentTimeMillis(); //打印时间间隔：毫秒
////                System.out.println(l1.queryEarliestAvailable(0));
////                prev = cur;
//            }
//        });
//        tpe.shutdown();
    }


    void acquireTest() {
        PriorityQueue<NewRateLimiter> queue = new PriorityQueue<>((o1, o2) ->
                (int) (o1.queryEarliestAvailable(0) - o2.queryEarliestAvailable(0)));
        queue.offer(NewRateLimiter.create(10));
        queue.offer(NewRateLimiter.create(10));
        queue.offer(NewRateLimiter.create(10));

    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        Long t = 9223372036854L;
        System.out.println(t / (60 * 60 * 24 * 365));

    }

}