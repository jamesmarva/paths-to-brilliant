package objects;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 14:21
 */
public class DemoClassWithTwoInteger implements Serializable {

    private Integer i0;

    private Integer i1;

    public DemoClassWithTwoInteger(){}

    public DemoClassWithTwoInteger(Integer i0, Integer i1) {
        this.i0 = i0;
        this.i1 = i1;
    }

}
