package ch20.sec1.improvment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 10:56
 **/
public class ProjectIterator implements IProjectIterator {

    private List<IProject> listOfProjects = new ArrayList<>();

    private int currentIndex = 0;

    public ProjectIterator(List<IProject> list) {
        this.listOfProjects = list;
    }

    @Override
    public boolean hasNext() {
        boolean res = true;
        if (this.currentIndex >= listOfProjects.size()
                || this.listOfProjects.get(this.currentIndex)  == null) {
            res = false;
        }
        return res;
    }

    @Override
    public Object next() {
        return (IProject) this.listOfProjects.get(this.currentIndex ++);
    }
}
