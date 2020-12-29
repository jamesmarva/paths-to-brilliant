package ch16.sec01.demo1;

/**
 * 丈夫类
 *
 * @author 王涵威
 * @date 20.12.5 19:53
 */
public class Husband implements IHandler {


    @Override
    public void handleMessage(IWomen women) {

        System.out.println("wife women ' s request is " + women.getRequest());
        System.out.println("Husbang's response is : yes");
    }
}
