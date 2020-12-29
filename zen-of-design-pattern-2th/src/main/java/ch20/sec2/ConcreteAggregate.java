package ch20.sec2;

import java.util.Vector;

/**
 * 代码清单20-12 具体容器
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-14 06:24
 **/
public class ConcreteAggregate implements Aggregate {

    private Vector<Object> vector = new Vector<>();

    @Override
    public void add(Object o) {
        this.vector.add(o);
    }

    @Override
    public void remove(Object o) {
        this.vector.remove(o);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(this.vector);
    }
}
