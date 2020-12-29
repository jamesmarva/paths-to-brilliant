package ch15.sec02;

/**
 * 代码清单15-17 调用者Invoker类
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 01:53
 **/
public class Invoker {

    private Command command;

    /**
     * 接受命令
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void action() {
        this.command.execute();
    }
}
