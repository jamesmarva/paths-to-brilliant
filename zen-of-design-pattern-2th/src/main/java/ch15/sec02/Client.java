package ch15.sec02;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 07:42
 **/
public class Client {

    public static void main(String[] args) {

        // declare a invoker
        Invoker invoker = new Invoker();

        // declare a receiver
        Receiver receiver = new ConcreteReceiver1();

        // declare a command with concret recevier
        Command command = new ConcreteCommand1(receiver);

        invoker.setCommand(command);

        invoker.action();


    }
}
