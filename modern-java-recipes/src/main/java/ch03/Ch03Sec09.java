package ch03;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 查找满足流的第一个元素的
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-02 01:02
 **/
public class Ch03Sec09 {

    public static void main(String[] args) {
        streamGetFirst();
        findFirstInEmptyStream();
        findAny();
    }

    /**
     * 查找第一个偶数
     */
    private static void streamGetFirst() {
        Optional<Integer> firstEven = Stream.of(2, 1, 3, 4, 5, 6, 6)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("firstEven: " + firstEven);
    }

    /**
     * findFirst 和 findAny 都是属于 短路终止操作。
     */
    private static void findFirstInEmptyStream() {
        Optional<Integer> firstEven = Stream.of(12,11, 213, 32, 43, 34, 5, 53, 34, 34, 34)
                .filter(n -> n > 100)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("firstEven: " + firstEven);
    }

    private static void findAny() {
        Optional<Integer> any = Stream.of(1, 2, 4, 5)
                .unordered()
                .findAny();

        Optional<Integer> any00 = Stream.of(12,3,44,5,5,5)
                .unordered()
                .parallel()
                .findAny();

        System.out.println(any + "" + any00);
    }
}
