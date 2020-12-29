package ch15.sec04;


/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-11 08:31
 **/
public abstract class Command {

    protected RequirementGroup rg = new RequirementGroup();

    protected PageGroup pg = new PageGroup();

    protected CodeGroup cg = new CodeGroup();

    /**
     * execute
     */
    public abstract void execute();
}
