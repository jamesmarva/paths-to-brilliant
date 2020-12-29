package ch23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 对象直接被 Thread 执行
 *
 * @author 王涵威
 * @date 20.12.29 21:28
 */
public class FutureTaskDemo0 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> f = new FutureTask<>(() -> 1 + 2);
        Thread t = new Thread(f);
        t.start();
        System.out.println(f.get());

    }
}
