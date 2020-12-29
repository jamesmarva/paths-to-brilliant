package ch15.sec05.my;

import ch15.sec05.Receiver;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-12 11:03
 **/
public interface Command {

    Receiver receiver = null;

    void execute();
}
