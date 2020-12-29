package ch15.sec04;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 08:29
 **/
public class AddRequirementCommand extends Command {

    @Override
    public void execute() {
        super.rg.find();

//       adding requirement
        super.rg.add();

//      add page
        super.pg.add();

//      add code
        super.cg.add();

//      give a plan
        super.rg.plan();
    }
}
