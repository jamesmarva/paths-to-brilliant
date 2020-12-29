package ch03;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;




/**
 * 利用reduce方法进行校验排序
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-29 02:37
 **/
public class Ch03Sec04 {


    public static void main(String[] args) {
        addBigDecimalByReduce();
        checkListSortedRight();
    }

    /**
     * 利用 reduce 方法对BigDeciaml 求和
     */
    public static void addBigDecimalByReduce() {
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                                .limit(10)
                                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));
        System.out.println("The total is " + total);
    }


    /**
     * 根据字符串长度对字符串list排序
     */
    public static void orderByStrLen() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        List<String> afterSorted = strings.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

    }

    public static void checkListSortedRight() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        List<String> afterSorted = strings.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        afterSorted.stream()
                .reduce((pre, cur) -> {
                   assert pre.length() <= cur.length();
                   return cur;
                });
    }




}
