package ch23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 *
 * 提交Runnable 的任务 带着
 *
 * @author 王涵威
 * @date 20.12.28 11:25
 */
public class FutureDemo0 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);

        Result r = new Result();
        r.setA("aaaaa");
        Future<Result> future = es.submit(new Task(r), r);


    }

}

class Result {
    private String a;

    private String b;


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}

class Task implements Runnable {

    Result result;

    Task(Result r) {
        this.result = r;
    }

    @Override
    public void run() {
        result.setB("afasdfasdf");
    }
}
