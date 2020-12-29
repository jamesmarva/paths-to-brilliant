package sec01;

import java.util.*;
import java.util.stream.Collectors;

public class Listing0601 {
    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
                                                    new Transaction(Currency.USD, 2300.0),
                                                    new Transaction(Currency.GBP, 9900.0),
                                                    new Transaction(Currency.EUR, 1100.0),
                                                    new Transaction(Currency.JPY, 7800.0),
                                                    new Transaction(Currency.CHF, 6700.0),
                                                    new Transaction(Currency.EUR, 5600.0),
                                                    new Transaction(Currency.USD, 4500.0),
                                                    new Transaction(Currency.CHF, 3400.0),
                                                    new Transaction(Currency.GBP, 3200.0),
                                                    new Transaction(Currency.USD, 4600.0),
                                                    new Transaction(Currency.JPY, 5700.0),
                                                    new Transaction(Currency.EUR, 6800.0) );

    public static void main(String[] args) {
        groupFunctionally();
        groupImperatively();
        // Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
        //     .collect(Collections.groupingBy(Transaction::getCurrency));
    }


    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println("transactionsByCurrencies: " + transactionsByCurrencies);
    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction item : transactions) {
            Currency tmpCurrency = item.getCurrency();
            List<Transaction> tmpTransactions = transactionsByCurrencies.computeIfAbsent(tmpCurrency, k -> new ArrayList());
            tmpTransactions.add(item);
        }
        System.out.println("transactionsByCurrencies: " + transactionsByCurrencies);
    }


    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR,
        USD,
        JPY,
        GBP,
        CHF
    }

}