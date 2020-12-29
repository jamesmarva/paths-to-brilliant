package ch15.sec01.improvement;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 00:46
 **/
public class Client1 {

    public static void main(String[] args) {

        Invoker invoker = new Invoker();

        System.out.println("-------- client delete a page --");

        Command command = new AddRequirementCommand();

        invoker.setCommand(command);

        invoker.action();

    }
}
