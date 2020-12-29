package ch15.sec01;

/**
 * 代码清单15-1 抽象组
 * 大家看抽象类中的每个方法，其中的每个都是一个命令语气——“找到它，增加，删除，给我计划！”
 * 这些都是命令，给出命令然后由相关的人员去执行。
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-10 01:26
 **/
public abstract class Group {


    /**
     * find the group
     */
    public abstract void find();


    /**
     * add function
     */
    public abstract void add();


    /**
     * delete function
     */
    public abstract void delete();


    /**
     * change function
     */
    public abstract void change();


    /**
     *
     */
    public abstract void plan();
}
