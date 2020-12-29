package ch20.sec2;


/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-14 06:27
 **/
public class Client {

    public static void main(String[] args) {
        Aggregate agg = new ConcreteAggregate();

        agg.add("adadfa");
        agg.add("aa");

        agg.add("bb");

        Iterator iterator = agg.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
