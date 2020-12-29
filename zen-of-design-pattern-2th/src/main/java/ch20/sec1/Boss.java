package ch20.sec1;

import java.util.ArrayList;
import java.util.List;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 02:56
 **/
public class Boss {

    public static void main(String[] args) {
        List<IProject> projectsList = new ArrayList<>();
        projectsList.add(new Project("star war", 10, 10000));
        projectsList.add(new Project("change time", 100, 1000000));
        projectsList.add(new Project("super man ", 1000, 100000000));

        for(int i = 0; i < 100; i++) {
            projectsList.add(new Project(i + "project", i * 10, i * 1000));
        }

        for (IProject p : projectsList) {
            System.out.println(p.getProjectInfo());
        }
    }
}
