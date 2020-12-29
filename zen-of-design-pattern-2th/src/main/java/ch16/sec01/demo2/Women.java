package ch16.sec01.demo2;

/**
 *
 * @author 王涵威
 * @date 20.12.5 20:41
 */
public class Women implements IWomen {

    private final int state;

    private String requestMessage;

    public Women(int state, String requestMessage) {
        this.state = state;
        switch (state) {
            case State.NOT_MARRIED:
                this.requestMessage = "daughter's request " + requestMessage;
                break;
            case State.MARRIED:
                this.requestMessage = "wife's request " + requestMessage;
                break;
            case State.HUSBAND_DEAD:
                this.requestMessage = "mother's request " + requestMessage;
                break;
            default:

        }
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public String request() {
        return this.requestMessage;
    }

    public static class State {

        public static final int NOT_MARRIED = 1;

        public static final int MARRIED = 2;

        public static final int HUSBAND_DEAD = 3;
    }
}
