package ch15.sec01.improvement;

import ch15.sec01.CodeGroup;
import ch15.sec01.PageGroup;
import ch15.sec01.RequirementGroup;

/**
 * 代码清单15-7 抽象命令类
 *
 * @author 王涵威
 * @date 20.12.10 22:21
 */
public abstract class Command {

    protected RequirementGroup rg = new RequirementGroup();

    protected PageGroup pg = new PageGroup();

    protected CodeGroup cg = new CodeGroup();

    /**
     * 执行命令
     */
    public abstract void execute();
}
