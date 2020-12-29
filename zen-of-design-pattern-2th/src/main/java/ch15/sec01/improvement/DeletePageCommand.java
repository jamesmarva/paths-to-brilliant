package ch15.sec01.improvement;

/**
 *
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 00:30
 **/
public class DeletePageCommand extends Command {

    @Override
    public void execute() {

        super.pg.find();

        super.pg.delete();

        super.pg.plan();
    }
}
