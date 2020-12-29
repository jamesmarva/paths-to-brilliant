package ch07;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-26 23:24
 **/
public class Ch07Sec01 {

    public static void main(String[] args) {
//        System.out.println("sequental: " + sequentialSum(100));
//
//        System.out.println("iterativeSum: " + iterativeSum(100));
//
//        System.out.println("parallelSum: " + parallelSum(100));


        System.out.println("Sequential sum done in:" +
                measureSumPerf(Ch07Sec01::sequentialSum, 20_000_000) + " msecs");

        System.out.println("iterativeSum sum done in:" +
                measureSumPerf(Ch07Sec01::iterativeSum, 20_000_000) + " msecs");

        System.out.println("parallelSum sum done in:" +
                measureSumPerf(Ch07Sec01::parallelSum, 20_000_000) + " msecs");

        System.out.println("sequentialSumWithRangeClosed sum done in:" +
                measureSumPerf(Ch07Sec01::sequentialSumWithRangeClosed, 20_000_000) + " msecs");

        System.out.println("parallelSumWithRangeClosed sum done in:" +
                measureSumPerf(Ch07Sec01::parallelSumWithRangeClosed, 20_000_000) + " msecs");


    }




    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L , Long::sum);
    }

    /**
     * 将顺序流转换为并行流
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }


    public static long sequentialSumWithRangeClosed(long n) {
        return LongStream.rangeClosed(1L, n)
                .limit(n)
                .reduce(0L , Long::sum);
    }

    /**
     * 将顺序流转换为并行流
     */
    public static long parallelSumWithRangeClosed(long n) {
        return LongStream.rangeClosed(1L, n)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }


    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }


}
