package ch23;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 实现最优的“烧水泡茶”程序
 *
 *
 * 初中语文课文里有一篇著名数学家华罗庚先生的文章《统筹方法》
 * 用两个线程 T1 和 T2 来完成烧水泡茶程序，
 * T1 负责洗水壶、烧开水、泡茶这三道工序，
 * T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。
 *
 * @author 王涵威
 * @date 20.12.30 14:34
 */
public class MakeTeaDemo1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        FutureTask<String> fetchTea = new FutureTask<>(() -> {
            latch.await();
            System.out.println("洗茶壶");
            Thread.sleep(100L);
            System.out.println("洗茶杯");
            Thread.sleep(200L);
            System.out.println("拿茶叶");
            Thread.sleep(100L);
            return "大红袍";
        });


        FutureTask<String> boilWater = new FutureTask<>(() -> {
            System.out.println("wash kettle");
            Thread.sleep(100L);
            System.out.println("boil water");
            latch.countDown();
            Thread.sleep(1500L);
            return "水开了, today take tea: ";
        });


        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("brilliant wang make tea").build();

        int numOfProcessors = Runtime.getRuntime().availableProcessors();

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(numOfProcessors,
                numOfProcessors * 2 + 1,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                tf);
        tpe.submit(fetchTea);
        tpe.submit(boilWater);
        try {
            String water = boilWater.get(10, TimeUnit.SECONDS);
            String tea = fetchTea.get(10, TimeUnit.SECONDS);
            System.out.println(water + tea);
        } catch (InterruptedException ig) {
        } catch (ExecutionException ig) {
        } catch (TimeoutException ig) {
        }
    }
}

