package ch16.sec01.demo1;

/**
 * 父亲类
 *
 * @author 王涵威
 * @date 20.12.5 19:50
 */
public class Father implements IHandler {

    /**
     * 未出嫁的女儿来请示父亲
     * @param women 古时候女性
     */
    @Override
    public void handleMessage(IWomen women) {
        System.out.println("女儿（women）的请求是：" + women.getRequest());
        System.out.println("父亲的回答是：同意");

    }
}
