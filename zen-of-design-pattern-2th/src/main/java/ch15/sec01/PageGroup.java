package ch15.sec01;

/**
 *  代码清单15-3 美工组
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-10 01:44
 **/
public class PageGroup extends Group {
    @Override
    public void find() {
        System.out.println("find the page group.");
    }

    @Override
    public void add() {
        System.out.println("add a page.");

    }

    @Override
    public void delete() {
        System.out.println("delete a page.");

    }

    @Override
    public void change() {
        System.out.println("change a page.");

    }

    @Override
    public void plan() {
        System.out.println("change plan.");
    }
}
