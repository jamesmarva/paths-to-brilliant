package ch16.sec01.demo2;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.5 21:37
 */
public class SonHandler extends Handler {

    public SonHandler() {
        super(Handler.SON_LEVEL);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("\n----------- mother request son ---------- ");
        System.out.println(women.request());
        System.out.println("son's response is agree");
    }
}
