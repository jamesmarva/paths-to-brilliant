package ch11.sec05;

import ch11.sec04.Discount;
import ch11.sec04.Quote;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-26 21:35
 **/
public class BeastPriceFinnderV5 {

    public static void main(String[] args) {
        BeastPriceFinnderV5 beastPriceFinnderV5 = new BeastPriceFinnderV5();
        beastPriceFinnderV5.findPriceStream("iPhone")
                .map(f -> f.thenAccept(System.out::println));
        // 你可以把构成 Stream 的所有 CompletableFuture<Void> 对象放到一个数组中，等待所有的任务执行完成，
        CompletableFuture[] futures = beastPriceFinnderV5.findPriceStream("iPhone")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        CompletableFuture.anyOf(futures).join();
    }

    private final List<Shop05> shops = Arrays.asList(new Shop05("BestPrice"),
            new Shop05("LetsSaveBig"),
            new Shop05("MyFavoriteShop"),
            new Shop05("BuyItAll"),
            new Shop05("ShopEasy")

    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    public Stream<CompletableFuture<String>> findPriceStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                            () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor))
                        );
    }



}
