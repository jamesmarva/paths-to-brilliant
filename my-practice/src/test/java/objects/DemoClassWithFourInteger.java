package objects;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 14:32
 */
public class DemoClassWithFourInteger implements Serializable {

    private Integer i0;
    private Integer i1;
    private Integer i2;
    private Integer i3;

    public DemoClassWithFourInteger() {}

    public DemoClassWithFourInteger(Integer i0, Integer i1, Integer i2, Integer i3) {
        this.i0 = i0;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
    }
}
