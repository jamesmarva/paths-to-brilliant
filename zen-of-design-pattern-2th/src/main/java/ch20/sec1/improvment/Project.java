package ch20.sec1.improvment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 10:43
 **/
public class Project implements IProject {

    private List<IProject> listOfProjects = new ArrayList<>();

    private String name = "";

    private int num = 0;

    private int cost = 0;

    public Project() { }

    private Project(String name, int num, int cost) {
        this.name = name;
        this.num = num;
        this.cost = cost;
    }

    @Override
    public void add(String name, int num, int cost) {
        this.listOfProjects.add(new Project(name, num, cost));
    }

    @Override
    public String getProjectInfo() {
        return "project name is" + this.name + "\n" +
                "project the num of people is " + this.num + "\n" +
                "project the cost of project is " + this.cost;
    }

    @Override
    public IProjectIterator getIterator() {
        return new ProjectIterator(this.listOfProjects);
    }

}
