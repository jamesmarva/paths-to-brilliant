package objects;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 14:13
 */
public class DemoClassWithInteger implements Serializable {

    private Integer integer;

    public DemoClassWithInteger() {}

    public DemoClassWithInteger(Integer i) {
        this.integer  = i;
    }

}
