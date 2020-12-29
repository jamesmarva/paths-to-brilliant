package ch03;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取流元素的数量
 *
 * @author 王涵威
 * @date 2020/6/29 21:42
 */
public class Ch03Sec07 {


    public static void main(String[] args) {
        countOfStream();
        countByMap();
        countByCollect();
    }

    /**
     *
     */
    public static void countOfStream() {
        long count = Stream.of(1, 2, 3, 4, 1,1,1,1,1,1)
                .count();
        System.out.println("count: " + count);
    }

    /**
     * 获取流中的元素的个数
     */
    public static void countByMap() {
        long count = Stream.of(1, 2, 3, 4, 1,1,1,1,1,1)
                .mapToLong(n -> 1L)
                .sum();
        System.out.println("count: " + count);
    }

    public static void countByCollect() {
        long count = Stream.of(1, 2, 3, 4, 1,1,1,1,1,2, 3, 434, 1)
                .collect(Collectors.counting());
        System.out.println("count: " + count);

    }
}
