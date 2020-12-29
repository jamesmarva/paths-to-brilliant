package ch15.sec05;

import java.util.Objects;

/**
     *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-12 07:18
 **/
public class Invoker {
    private Command command;

    public Invoker() {

    }

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        Objects.requireNonNull(this.command, "the command is null").execute();
    }

}
