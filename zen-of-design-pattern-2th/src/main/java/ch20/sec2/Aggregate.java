package ch20.sec2;

/**
 * 代码清单20-11 抽象容器
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-14 06:23
 **/
public interface Aggregate {

    void add(Object o);

    void remove(Object o);

    /**
     *
     * @return
     */
    Iterator getIterator();
}
