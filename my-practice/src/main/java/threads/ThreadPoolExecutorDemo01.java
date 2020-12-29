package threads;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 2020/6/29 11:53
 */
public class ThreadPoolExecutorDemo01 {

    //之所以要保证队列中起码有一个元素，就是要让保证线程是处于满载的状态的
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(1000) {
        @Override
        public boolean offer(Runnable o) {
            if (size() == 0) {
                return super.offer(o);
            } else {
                return false;
            }
        }
    };

    private int cpuNum = Runtime.getRuntime().availableProcessors();

    private ThreadPoolExecutor tpe = new ThreadPoolExecutor(0,
            cpuNum + 1,
            60,
            TimeUnit.SECONDS,
            queue);

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutorDemo01 tt = new ThreadPoolExecutorDemo01();
        tt.useThreadPool();
    }

    public void useThreadPool() {

        tpe.setRejectedExecutionHandler((r, executor) -> {
            try {
                // put() 是一个阻塞的方法，存在长时间阻塞的风险，
                executor.getQueue().put(r);
                if (executor.isShutdown()) {
                    throw new RejectedExecutionException("Task" + r + "rejected from " + executor);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 1000; i++) {
            final int tempI = i;
            tpe.execute(() -> {
                System.out.println(Thread.currentThread() + "" + tempI);
            });
        }
    }

    private class MyThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public MyThreadFactory(String feature) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "From MyThreadFactory's " + feature + "-Worker-";
        }

        @Override
        public Thread newThread(Runnable r) {
            String name = namePrefix + threadNumber.getAndIncrement();
            Thread t = new Thread(group, r, name, 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            String name = namePrefix + threadNumber.getAndIncrement();
            Thread t = new Thread(group, r, name, 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
