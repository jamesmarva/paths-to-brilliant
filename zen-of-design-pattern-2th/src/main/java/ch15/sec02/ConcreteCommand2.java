package ch15.sec02;

/**
 * 定义了两个具体的命令类，读者可以在实际应用中扩展该命令类。
 * 在每个命令类中，通过构造函数定义了该命令是针对哪一个接收者发出的，
 * 定义一个命令接收的主体
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 01:48
 **/
public class ConcreteCommand2 extends Receiver {

    private Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doSomething() {
        this.receiver.doSomething();
    }
}
