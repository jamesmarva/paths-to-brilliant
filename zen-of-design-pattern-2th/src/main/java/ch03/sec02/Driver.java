package ch03.sec02;

/**
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-08 08:54
 **/
public class Driver implements IDriver {

    @Override
    public void drive(ICar car) {
        car.run();
    }
}
