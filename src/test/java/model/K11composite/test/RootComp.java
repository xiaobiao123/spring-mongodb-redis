package model.K11composite.test;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class RootComp extends AbsComp {

    private List<AbsComp> list = Lists.newArrayList();

    @Override
    protected void add(AbsComp absComp) {
        list.add(absComp);
    }

    @Override
    protected void remove(AbsComp absComp) {
        list.remove(absComp);
    }

    @Override
    protected void eachChild() {
        System.out.println(name+"xxxxxxxxxxxxxxxxxxxx");
        for (AbsComp absComp1 : list) {
            System.out.println(absComp1.name + "========================");
            absComp1.eachChild();
        }
    }
}
