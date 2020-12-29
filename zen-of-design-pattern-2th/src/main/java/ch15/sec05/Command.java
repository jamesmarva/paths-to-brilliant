package ch15.sec05;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.11 15:01
 */
public abstract class Command {

    protected final Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * execute the command
     */
    public abstract void execute();
}


