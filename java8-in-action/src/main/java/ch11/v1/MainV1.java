package ch11.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-23 00:26
 **/
public class MainV1 {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync("my favorite product");

        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        doSomethingElse();
        // while the price of the product is being calculated
        try {
            double price = future.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }
    private static void doSomethingElse() {
        System.out.println("Doing something else...");
    }
}