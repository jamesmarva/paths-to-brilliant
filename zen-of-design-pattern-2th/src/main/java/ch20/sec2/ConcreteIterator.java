package ch20.sec2;

import java.util.Vector;

/**
 * 代码清单20-10 具体迭代器
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-14 06:18
 **/
public class ConcreteIterator implements Iterator{

    private Vector vector = new Vector<>();

    public int cursor = 0;

    public ConcreteIterator(Vector vector) {
        this.vector = vector;
    }
    @Override
    public Object next() {
        if (this.hasNext()) {
            return this.vector.get(this.cursor++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.cursor != this.vector.size();
    }

    @Override
    public boolean remove() {
        this.vector.remove(this.cursor);
        return false;
    }
}
