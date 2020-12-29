package ch20.sec1.improvment;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 11:23
 **/
public class Boss {

    public static void main(String[] args) {
        IProject project = new Project();
        project.add("star war", 10, 10000);
        project.add("star war", 10, 10000);

        project.add("star war", 10, 10000);
        for (int i = 0; i < 10000; i++) {
            project.add("project " + i, i *1000, i*1000000);
        }
        IProjectIterator projectIterator = project.getIterator();
        while (projectIterator.hasNext()) {
            IProject p = (IProject) projectIterator.next();
            System.out.println(p.getProjectInfo());
        }
    }
}
