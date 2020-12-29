package ch15.sec04;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 08:44
 **/
public abstract class Group {

    /**
     * find it
     */
    public abstract void find();

    public abstract void add();

    public abstract void delete();

    public abstract void change();

    public abstract void plan();

    public void rollBack() {
        System.out.println("rollback the execution");
    }

}
