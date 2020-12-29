package ch04;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分区和分组
 * 希望将 元素集合分为若干个类别。
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-16 03:05
 **/
public class Ch04Sec05 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of", "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> System.out.println(key + ": " + value));
        

    }
}
