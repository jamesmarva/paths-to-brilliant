package ch20.sec1;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 02:53
 **/
public class Project implements IProject {

    private String name = "";

    private int num = 0;

    private int cost = 0;

    public Project(String name, int num , int cost) {
        this.name = name;
        this.num = num;
        this.cost = cost;
    }

    @Override
    public String getProjectInfo() {
        return "project name is" + this.name + "\n" +
                "project the num of people is " + this.num + "\n" +
                "project the cost of project is " + this.cost;
    }
}
