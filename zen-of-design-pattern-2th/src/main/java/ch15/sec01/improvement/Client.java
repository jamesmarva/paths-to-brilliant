package ch15.sec01.improvement;

/**
 * <功能描述><br>
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 00:39
 **/
public class Client {

    public static void main(String[] args) {
        // --- define our invoker -----
        Invoker invoker = new Invoker();

        System.out.println("--------- client -------------------");

        Command command = new AddRequirementCommand();

        invoker.setCommand(command);

        invoker.action();

    }
}
