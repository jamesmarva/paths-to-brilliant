package ch03.sec02;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-08 09:02
 **/
public class Sec02Client {

    public static void main(String[] args) {

        IDriver zhangSan = new Driver();
        zhangSan.drive(new BenZ());

        zhangSan.drive(new BMW());
    }
}
