package ch15.sec05;


/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-12 07:16
 **/
public class PerfectClient {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        Command command = new ConcreteCommand1();

        invoker.setCommand(command);
    }
}
