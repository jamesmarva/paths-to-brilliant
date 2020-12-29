package ch20.sec1.improvment;

/**
 *
 * 代码清单20-4 项目信息接口
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-13 10:27
 **/
public interface IProject {

    /**
     * add
     */
    void add(String name, int num, int cost);

    /**
     * get project information
     * @return
     */
    String getProjectInfo();

    /**
     * get a Iterator of 
     * @return
     */
    IProjectIterator getIterator();

}
