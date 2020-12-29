package ch11.sec04;

import ch11.ExchangeService;
import ch11.v2.ShopV2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-24 02:04
 **/
public class BestPriceFinder {

    private final List<ShopV2> shops = Arrays.asList(new ShopV2("BestPrice"),
            new ShopV2("LetsSaveBig"),
            new ShopV2("MyFavoriteShop"),
            new ShopV2("BuyItAll"),
            new ShopV2("ShopEasy")

    );

    public List<String> findPriceInUsd(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (ShopV2 item : shops) {
            CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> item.getPrice(product))
                        .thenCombine(
                            CompletableFuture.supplyAsync(
                                () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)
                            ),
                            (price, rate) -> price * rate
                        );
            priceFutures.add(futurePriceInUSD);

        }
        List<String> prices = priceFutures.stream().parallel()
                .map(CompletableFuture::join)
                .map(price -> " price is " + price)
                .collect(Collectors.toList());
        return prices;
    }


    /**
     * 用 Java 7 来实现上面这个并行行活动
     * @param product
     * @return
     */
    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (ShopV2 item : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    try {
                        double priceInEUR = item.getPrice(product);
                        return priceInEUR * futureRate.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> item : priceFutures) {
            try {
                prices.add(" price is " + item.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }
}
