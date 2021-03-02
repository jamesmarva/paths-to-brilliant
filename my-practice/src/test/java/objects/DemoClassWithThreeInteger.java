package objects;

import org.apache.log4j.xml.SAXErrorHandler;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.2 14:25
 */
public class DemoClassWithThreeInteger implements Serializable {

    private Integer i0;

    private Integer i1;

    private Integer i2;

    public DemoClassWithThreeInteger() {}

    public DemoClassWithThreeInteger(Integer i0, Integer i1, Integer i2) {
        this.i0 = i0;
        this.i1 = i1;
        this.i2 = i2;
    }

}
