package model.K1composite.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public abstract class AbsComp {

    public String name;

    protected abstract void add(AbsComp absComp);

    protected abstract void remove(AbsComp absComp);

    protected abstract void eachChild();
}
