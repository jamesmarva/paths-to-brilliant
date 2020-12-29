package ch15.sec05;

/**
 *
 * @author 王涵威
 * @date 20.12.11 15:03
 */
public class ConcreteCommand1 extends Command {

    public ConcreteCommand1() {
        super(new ConcreteReceiver1());
    }

    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
    }


    @Override
    public void execute() {

    }
}
