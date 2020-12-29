package ch15.sec04;

/**
 * 代码清单15-21 撤销命令
 * @author 王涵威
 * @date 20.12.11 14:06
 */
public class CancelDeletePageCommand extends Command {

    @Override
    public void execute() {
        super.pg.rollBack();
    }
}
