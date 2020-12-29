package ch16.sec01.demo2;

/**
 *
 *
 *
 * @author 王涵威
 * @date 20.12.5 21:32
 */
public class FatherHandler extends Handler {


    public FatherHandler() {
        super(Handler.FATHER_LEVEL);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("\n----------- daughter request father. -----------");
        System.out.println(women.request());
        System.out.println("Father's response is agree");
    }
}
