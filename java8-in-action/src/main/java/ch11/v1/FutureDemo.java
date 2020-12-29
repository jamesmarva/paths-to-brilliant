package ch11.v1;

import java.util.concurrent.*;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-23 00:33
 **/
public class FutureDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongCompution();
            }
        });
        doSomethingElse();
        try {
            Double res = future.get(2L, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    public static void  doSomethingElse() throws InterruptedException {
        Thread.sleep(1000L);
        return;
    }

    private static double doSomeLongCompution() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {

        }
        return 1.0;
    }
}
