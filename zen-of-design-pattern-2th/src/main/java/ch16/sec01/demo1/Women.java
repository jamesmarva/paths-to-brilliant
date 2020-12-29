package ch16.sec01.demo1;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.5 19:32
 */
public class Women implements IWomen {

    /**
     * 返回一个int类型的结果，来描述妇女的个人状况
     * 1    未出家
     * 2    出嫁
     * 3    夫死
     */
    private int type;

    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }

    public class State {

        public static final int NOT_MARRIED  = 0;

        public static final int MARRIED = 1;

        public static final int HUSBAND_DEAD = 2;
    }
}
