package ch03;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * 用户希望通过流操作生成单一值
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-06-27 21:50
 **/
public class Ch03Sec03 {

    public static void main(String[] args) {
        reduceDemo01();
        addPowTwoWrong();
        addPowTwoRight();
        usingBinaryOperationToReduction();
        intStreamDemo01();
        stringConcatInReduce();
        System.out.println("-------------------------------");
        stringConcatByCollect();
        stringConcatUseJoining();
    }


    private void reduceDemo() {
        String[] stringArray = "this is an array of Strings.".split(" ");
        long countt = Arrays.stream(stringArray).map(String::length).count();

        System.out.println("long: " +   countt);

        int totalLength = Arrays.stream(stringArray).mapToInt(String::length).sum();

        System.out.println("sum : " + totalLength);

        OptionalDouble average  = Arrays.stream(stringArray).mapToInt(String::length).average();

        OptionalInt max = Arrays.stream(stringArray).mapToInt(String::length).max();

        OptionalInt min = Arrays.stream(stringArray).mapToInt(String::length).min();
    }

    /**
     *
     */
    private static void reduceDemo01() {
        int sum = IntStream.rangeClosed(1, 10).reduce((x, y) -> {
            System.out.printf("x=%d, y=%d \n", x, y);
            return x + y;
        }).orElse(0);
        System.out.println("sum: " + sum);

    }

    private static void addPowTwoWrong() {
        int doubleSum = IntStream.rangeClosed(1, 10).reduce((x, y) -> x + 2 * y).orElse(0);
        System.out.println("doubleSum: " + doubleSum);
    }


    private static void addPowTwoRight() {
        int doubleSum = IntStream.rangeClosed(1, 10).reduce(0, (x, y) -> x + 2 * y);
        System.out.println("oubleSum: " + doubleSum);
    }

    /**
     * 利而二元运算执行归约操作
     */
    private static void usingBinaryOperationToReduction() {
        int sum = Stream.of(1, 2, 4, 5, 5, 6, 7, 7, 8)
                        .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static void intStreamDemo01() {
        int sum = IntStream.of(1, 2, 3, 4, 5,6,6,777, 8).sum();
        System.out.println(sum);
        OptionalInt max = IntStream.of(1, 2, 3, 4, 5,6,6,777, 8).max();

        System.out.println(max.getAsInt());
    }

    /**
     *
     * 效率不好，因为字符串拼接会频繁创建和销毁对象
     */
    private static void stringConcatInReduce() {
        String s = Stream.of("this", "is", "a", "list").reduce("", String::concat);
        System.out.println(s);

    }

    private static void stringConcatByCollect() {
        String s = Stream.of("this", "is", "a", "list")
                .collect(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();
        System.out.println(s);
    }

    private static void stringConcatCollectSimplify() {
        String s = Stream.of("this", "is", "a", "list")
                        .collect(StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append)
                        .toString();
        System.out.println("s: " + s);
    }

    private static void stringConcatUseJoining() {
        String s = Stream.of("this", "is", "a", "list")
                        .collect(Collectors.joining(" "));
        System.out.println("s: " + s);
    }
    private static void addBookToMap() {
        List<Book> books = new ArrayList<>();
        HashMap<Integer, Book> bookMap = books.stream()
                .reduce(new HashMap<Integer, Book>(),
                        (map, book) -> {
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });
    }


    private class Book {
        private Integer id;
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
