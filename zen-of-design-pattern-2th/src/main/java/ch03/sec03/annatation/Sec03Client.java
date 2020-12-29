package ch03.sec03.annatation;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-09 12:12
 **/
public class Sec03Client {

    public static void main(String[] args) {
        IDriver driver = new Driver();
        driver.drive(new BenZ());

        driver.drive(new BMW());
    }
}
