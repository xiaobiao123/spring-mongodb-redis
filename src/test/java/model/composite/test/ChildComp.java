package model.composite.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class ChildComp extends AbsComp {
    @Override
    protected void add(AbsComp absComp) {
        System.out.println("add.....................");
    }

    @Override
    protected void remove(AbsComp absComp) {
        System.out.println("remove.....................");
    }

    @Override
    protected void eachChild() {
        System.out.println(name + "...............................");

    }
}
