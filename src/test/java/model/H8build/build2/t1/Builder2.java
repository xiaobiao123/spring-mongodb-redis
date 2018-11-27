package model.H8build.build2.t1;

/**
 * Created by gwb on 2018/6/14.
 */
public class Builder2 implements ProductBuilder {
    @Override
    public void addName(String name) {
        ss.addObject(name);
    }

    @Override
    public void addAge(String name) {
        ss.addObject(name);
    }

    @Override
    public void addSex(String name) {
        ss.addObject(name);
    }

    @Override
    public void priSS() {
        ss.getObject();
    }
}
