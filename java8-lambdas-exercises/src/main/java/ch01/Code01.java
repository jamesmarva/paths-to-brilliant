package ch01;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 *
 *
 * @author 王涵威
 * @date 21.6.10 11:17
 */
public class Code01 {


    public static void main(String[] args) {
        Runnable noArgument = () -> System.out.println("hello james");
        ActionListener oneArgument = event -> System.out.println("Button clicked");
        Runnable muliStatement = () -> {
            System.out.println("hello");
            System.out.println("james");
        };
        BinaryOperator<Long> add = (x, y) -> x + y;

        BinaryOperator<Long> addExplicitType = (Long x, Long y) -> x + y;

    }

}
