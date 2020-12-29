package ch15.sec04;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 20.12.11 14:30
 */
public class DeleteCodeCommand extends Command {

    @Override
    public void execute() {
        cg.delete();
    }
}
