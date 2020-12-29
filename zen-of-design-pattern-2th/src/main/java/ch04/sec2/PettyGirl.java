package ch04.sec2;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-09 13:07
 **/
public class PettyGirl implements IPrettyGirl {
    private String name;

    public PettyGirl(String name) {
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println();
    }

    @Override
    public void niceFigure() {
        System.out.println();

    }

    @Override
    public void greatTemerament() {
        System.out.println();

    }
}
