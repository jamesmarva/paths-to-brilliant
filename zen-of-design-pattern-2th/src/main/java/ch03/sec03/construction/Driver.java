package ch03.sec03.construction;

import ch03.sec03.ICar;
import ch03.sec03.IDriver;

/**
 *
 * @author 王涵威
 * @date 20.12.8 22:03
 */
public class Driver implements IDriver {

    private ICar car;

    public Driver(ICar car) {
        this.car = car;
    }

    @Override
    public void drive() {
        this.car.run();
    }
}
