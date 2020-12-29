package ch15.sec02;

/**
 *  代码清单15-16 具体的Command类
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 01:46
 **/
public class ConcreteCommand1 extends Command {

    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}
