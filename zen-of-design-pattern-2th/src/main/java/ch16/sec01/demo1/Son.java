package ch16.sec01.demo1;

/**
 *
 * son class
 * @author 王涵威
 * @date 20.12.5 19:56
 */
public class Son implements IHandler {

    /**
     * mother request son message
     * @param women 古时候女性
     */
    @Override
    public void handleMessage(IWomen women) {
        System.out.println("Mother(women) request is: " + women.getRequest());
        System.out.println("Son's response is: agree");
    }
}
