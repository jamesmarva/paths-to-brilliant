package ch04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 对映射排序
 * 用户希望根据 键或者值 对map进行排序。
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-16 01:34
 **/
public class Ch04Sec04 {

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book(1, "Java 8 in Action", 49.99),
                new Book(2, "Java SE8 for the Really Impatient", 39.99),
                new Book(3, "Core Java Volume I -- Fundamentals", 43.30),
                new Book(4, "Functional Programming in Java", 27.64),
                new Book(5, "Making Java Groovy", 45.99),
                new Book(6, "Head First Java", 26.97),
                new Book(7, "Effective Java", 35.47),
                new Book(8, "Java 8 Pocket Guide", 10.40),
                new Book(9, "Gradle Recipes for Android", 23.76),
                new Book(10, "Spring Boot in Action", 39.97)
        );
        Map<Integer, Book> bookMap = books.stream()
                                        .collect(Collectors.toMap(Book::getId, b -> b));

//        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));

        bookMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        bookMap = books.stream()
                    .collect(Collectors.toMap(Book::getId, Function.identity()));
        
//        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));

    }
    
}