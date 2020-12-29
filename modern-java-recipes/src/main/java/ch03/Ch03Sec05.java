package ch03;

import java.util.stream.IntStream;

/**
 * 利用 peek 方法对流进行调试
 *
 * @author 王涵威
 * @date 2020/6/29 21:10
 */
public class Ch03Sec05 {

    public static void main(String[] args) {

        System.out.println(sumDoubleDivisibleBy3(100, 120));
        System.out.println(sumDoubleDivisibleBy3Demo1(100, 120));
        System.out.println(sumDoubleDivisibleBy3Demo2(100, 120));
    }


    /**
     * 对整数进行倍增，帅选与求和
     * @param start
     * @param end
     * @return
     */
    private static int sumDoubleDivisibleBy3(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();

    }


    /**
     * 添加标识映射以便打印
     * 答应流中的每个元素以便进行观察，让用户在不影响流处理的同时观察其内部操作。
     * @param start
     * @param end
     * @return
     */
    private static int sumDoubleDivisibleBy3Demo1(int start, int end) {
        return IntStream.rangeClosed(start, end)
                // 标识映射 打印并且返回每个元素
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();
    }

    /**
     * 用 peek 来输出每次流处理以后的值
     * @param start
     * @param end
     * @return
     */
    private static int sumDoubleDivisibleBy3Demo2(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .peek(n -> System.out.println("original: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("double : " + n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.println("filter: " + n))

                .sum();
    }


}
