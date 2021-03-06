package generic.demo1st;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.3.3 11:44
 */
public class Demo2nd {

    public void set(Inter in) {
        in.out();
    }

    public static void main(String[] args) {
        Inter i = new Inter1st();
        Demo2nd nd = new Demo2nd();
        nd.set(i);
    }

}
