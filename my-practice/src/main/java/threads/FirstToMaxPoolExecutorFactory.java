package threads;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 2020/6/29 15:03
 */
public class FirstToMaxPoolExecutorFactory {

//    private final static Logger logger =

    public static ExecutorService newPoolExecutor(int startSize,
                                                  int maxSize,
                                                  long time,
                                                  TimeUnit unit,
                                                  int queueSize,
                                                  String featureOfThread) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(startSize,
                                                        maxSize,
                                                        time,
                                                        unit,
                                                        newLinkedBlockingQueue(queueSize));
        tpe.setThreadFactory(new NewThreadFactory(featureOfThread));
        tpe.setRejectedExecutionHandler(newRejectExecutionHandler());
        return tpe;
    }

    private static RejectedExecutionHandler newRejectExecutionHandler() {
        return new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    executor.getQueue().put(r);
                    if (executor.isShutdown()) {
                        throw new RejectedExecutionException("Task" + r + "rejected from " + executor);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static BlockingQueue<Runnable> newLinkedBlockingQueue(int size) {
        return new LinkedBlockingQueue<Runnable>(size) {
            @Override
            public boolean offer(Runnable runnable) {
                if (size() == 0) {
                    return super.offer(runnable);
                } else {
                    return false;
                }
            }
        };
    }

    private static class NewThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public NewThreadFactory(String feature) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "From NewThreadFactory's " + feature + "-Worker-";
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
