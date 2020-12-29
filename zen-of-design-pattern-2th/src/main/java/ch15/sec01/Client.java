package ch15.sec01;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-10 09:06
 **/
public class Client {

    public static void main(String[] args) {
        System.out.println("---- client require add");
        Group gp = new RequirementGroup();
        gp.find();
        gp.add();
        gp.plan();
    }
}
