package ch16.sec01.demo2;


/**
 *
 *
 * @author 王涵威
 * @date 20.12.5 21:35
 */
public class HusbandHandler extends Handler {

    public HusbandHandler() {
        super(Handler.HUSBAND_LEVEL);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("\n------ wife request husband ------");
        System.out.println(women.request());
        System.out.println("husband's response is agree");
    }
}
