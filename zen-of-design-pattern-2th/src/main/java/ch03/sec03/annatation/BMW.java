package ch03.sec03.annatation;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-09 11:53
 **/
public class BMW implements ch03.sec03.annotation.ICar {

    @Override
    public void run() {
        System.out.println("BMW start running.");
    }
}
