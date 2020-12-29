package ch16.sec01.demo2;

/**
 *
 * @author 王涵威
 * @date 20.12.5 20:50
 */
public abstract class Handler {

    public static final int FATHER_LEVEL = 1;

    public static final int HUSBAND_LEVEL = 2;

    public static final int SON_LEVEL = 3;

    private int level = 0;

    private Handler next;

    public Handler(int level) {
        this.level = level;
    }

    /**
     *
     * @param women 用于判断女性的状态
     */
    public final void handleMessage(IWomen women) {
        if (women.getState() == this.level) {
            this.response(women);
        } else {
            if (this.next != null) {
                //
                this.next.handleMessage(women);
            } else {
                System.out.println("--- no next handler. --- ");
            }
        }
    }

    public void setNext(Handler handler) {
        this.next = handler;
    }

    /**
     * response the women
     * @param women be response.
     */
    protected abstract void response(IWomen women);
}