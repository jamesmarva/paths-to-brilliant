package ch23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 其实很简单，FutureTask 实现了 Runnable 和 Future 接口，
 * 由于实现了 Runnable 接口，所以可以将
 * FutureTask 对象作为任务提交给 ThreadPoolExecutor 去执行，
 * 也可以直接被 Thread 执行；又因为实现了 Future 接口，所以也能用来获得任务的执行结果。
 *
 * @author 王涵威
 * @date 20.12.29 21:24
 */
public class FutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> f = new FutureTask<>(() -> 1 + 2);

        ExecutorService es = Executors.newFixedThreadPool(1);

        es.submit(f);
        System.out.println(f.get());
    }
}
