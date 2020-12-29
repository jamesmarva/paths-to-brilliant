package ch11.sec05;

import ch11.sec04.Discount;

import java.util.Random;

import static ch11.Util.delay;
import static ch11.Util.format;

/**
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-26 22:08
 **/
public class Shop05 {

    private final String name;

    private final Random random;

    public  Shop05(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code  code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }
}
