package ch04.sec2;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-09 13:08
 **/
public abstract class AbstractSearcher {

    protected IPrettyGirl pettyGirl;

    public AbstractSearcher(IPrettyGirl pettyGirl) {
        this.pettyGirl = pettyGirl;
    }

    public abstract void show();
}
