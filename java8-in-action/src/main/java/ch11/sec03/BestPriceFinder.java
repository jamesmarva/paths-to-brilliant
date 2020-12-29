package ch11.sec03;

import ch11.v1.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-23 01:41
 **/
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")/*,
                                                   new Shop("ShopEasy")*/);
    private final Executor executor = Executors.newFixedThreadPool(shops.size(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });
    public List<String> findPriceSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPriceParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param product
     * @return
     */
    public List<String> findPriceFuture(String product) {
        List<CompletableFuture<String>> futures = shops.parallelStream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
                        + shop.getPrice(product), executor))
                .collect(Collectors.toList());

        return futures.parallelStream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


}
