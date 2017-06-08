package model.mediator.test;

/**
 * Created by gwb on 2017/6/7.
 * 国家
 */
public abstract class Country {
    protected UnitedNations mediator;

    public Country(UnitedNations mediator) {
        this.mediator = mediator;
    }

    public abstract void declare(String s);

    public abstract void getDeclare(String message);
}
