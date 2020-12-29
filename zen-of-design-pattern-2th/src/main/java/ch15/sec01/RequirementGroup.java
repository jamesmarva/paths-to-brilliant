package ch15.sec01;

/**
 * 代码清单15-2 需求组
 * 我们再看3个实现类，其中的需求组最重要，需求组RequirmentGroup类如代码清单15-2所示。
 * 
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-10 01:32
 **/
public class RequirementGroup extends Group {

    @Override
    public void find() {
        System.out.println("find the requirement group.");
    }

    @Override
    public void add() {
        System.out.println("client add a new requirement.");
    }

    @Override
    public void delete() {
        System.out.println("delete requirement.");
    }

    @Override
    public void change() {
        System.out.println("change the requirement.");
    }

    @Override
    public void plan() {
        System.out.println("Giving a plan.");
    }
}
