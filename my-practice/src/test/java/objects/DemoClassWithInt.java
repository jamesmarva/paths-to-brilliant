package objects;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 14:09
 */
public class DemoClassWithInt implements Serializable {

    private int field;

    public DemoClassWithInt(){}

    public DemoClassWithInt(int field) {
        this.field = field;
    }
}
