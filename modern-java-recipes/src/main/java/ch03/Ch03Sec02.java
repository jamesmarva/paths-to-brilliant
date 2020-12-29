package ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 利用基本类型的数据源创建流
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-27 20:43
 **/
public class Ch03Sec02 {

    public static void main(String[] args) {
        createStreamFormBoxed();
        createStreamFromMapToObj();
        createStreamFromCollect();
        createArrayFromStream();

    }

    private static void createStreamFormBoxed() {
        List<Integer> list = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                        .boxed()
                                        .collect(Collectors.toList());
        System.out.println("list; " + list);
    }

    private static void createStreamFromMapToObj() {
        List<Integer> list = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8)
                                        .mapToObj(Integer::valueOf)
                                        .collect(Collectors.toList());
        System.out.println("list: " + list);
    }

    /**
     * 用 collect来进行创建基本类型流
     */
    private static void createStreamFromCollect() {
        List<Integer> list = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8)
                                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("list: " + list);
    }

    private static void createArrayFromStream() {
        int[] intArray = IntStream.of(1, 2, 4, 4, 5, 7, 8, 9).toArray();
        System.out.println(Arrays.toString(intArray));
    }
}
