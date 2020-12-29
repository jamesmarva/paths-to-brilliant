package ch03.sec03.setter;

import ch03.sec03.ICar;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-09 11:27
 **/
public class Driver implements IDriver {

    private ICar car;

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }

    @Override
    public void driver() {
        this.car.run();
    }
}
