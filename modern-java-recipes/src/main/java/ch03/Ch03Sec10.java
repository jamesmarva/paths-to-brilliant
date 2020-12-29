package ch03;

import java.util.stream.IntStream;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-02 01:37
 **/
public class Ch03Sec10 {

    public static void main(String[] args) {

        Ch03Sec10 ch03Sec10 = new Ch03Sec10();
        ch03Sec10.anyMatchDemo();
        ch03Sec10.allMatchDemo();
        ch03Sec10.noneMatchDemo();


    }

    private Primes calculator = new Primes();

    private  void anyMatchDemo() {
        System.out.println(IntStream.of(2, 3, 5, 6, 7, 11, 13, 17, 19)
                .anyMatch(calculator::isPrime));

    }

    private void allMatchDemo() {
        System.out.println(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
                .allMatch(calculator::isPrime));

    }

    private void noneMatchDemo() {
        System.out.println(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
                .noneMatch(calculator::isPrime));

    }

    private static boolean isPrime(int num) {
        int limit = (int) (Math.sqrt(num) + 1);
        return (num == 2) ||
                (num > 1 && IntStream.range(2, limit).noneMatch(divisor -> num % divisor == 0));
    }

    private class Primes {

        public boolean isPrime(int num) {
            int limit = (int) (Math.sqrt(num) + 1);
            return (num == 2) ||
                    (num > 1 && IntStream.range(2, limit).noneMatch(divisor -> num % divisor == 0));
        }
    }
}
