package ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * flatMap 和  map  的方法
 * 多个值转成一个值，就是的用Stream.map()
 * 一个值转换成多个值，并且需要需要生成流 的 ‘展平’ 用 flatMap
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-07-13 01:34
 **/
public class Ch03Sec11 {


    public static void main(String[] args) {


        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");
        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        List<Customer> customers = Arrays.asList(sheridan, ivanova, garibaldi);

//        customers.stream()
//                .map(Customer::getName)
//                .forEach(System.out::println);
//        customers.stream()
//                .map(Customer::getOrders) // function<Customer,List<Order>>
//                .forEach(System.out::println);
//        customers.stream()
//                .map(Customer::toString)
//                .forEach(System.out::println);

        // 这里的都给拍扁了都。。
        customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .forEach(System.out::println);


        customers.stream()
                .flatMap(customer -> {
                    if (customer.getOrders().size() == 0) {
                        return Stream.empty();
                    } else {
                        return customer.getOrders().stream();
                    }
                })
                .forEach(System.out::println);
    }
}

class Customer {
    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Customer addOrder(Order order) {
        orders.add(order);
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}

class Order {
    private int id;

    public Order(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
