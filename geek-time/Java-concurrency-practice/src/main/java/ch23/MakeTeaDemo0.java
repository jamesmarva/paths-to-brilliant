package ch23;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.30 14:55
 */
public class MakeTeaDemo0 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> fetchTeaFutureTask = new FutureTask<>(new FetchTeaCallable());
        FutureTask<String> makeTeaCallableFutureTask = new FutureTask<>(new MakeTeaCallable(fetchTeaFutureTask));
        Thread t1 = new Thread(makeTeaCallableFutureTask);
        t1.start();
        Thread t2 = new Thread(fetchTeaFutureTask);
        t2.start();
        System.out.println(makeTeaCallableFutureTask.get());

    }

}

class MakeTeaCallable implements Callable<String> {

    private FutureTask<String> fetchTeaFuture;

    public MakeTeaCallable(FutureTask<String> f) {
        this.fetchTeaFuture = f;
    }

    @Override
    public String call() throws InterruptedException, ExecutionException {
        System.out.println("T1:洗水壶...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1:烧开水...");

        TimeUnit.SECONDS.sleep(15); // 获取T2线程的茶叶
        String tf = fetchTeaFuture.get();
        System.out.println("T1:拿到茶叶:"+tf);
        System.out.println("T1:泡茶...");
        return "上茶:" + tf;
    }
}

class FetchTeaCallable implements Callable<String> {
    
    @Override
    public String call() throws InterruptedException {
        System.out.println("洗茶壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("洗茶杯...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("拿茶叶...");
        TimeUnit.SECONDS.sleep(1);
        return "long jin";
    }

}

