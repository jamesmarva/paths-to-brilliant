package ch15.sec05;

/**
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-12 07:14
 **/
public class ConcreteCommand2 extends Command {

    public ConcreteCommand2() {
        super(new ConcreteReceiver2());
    }

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {

    }
}
