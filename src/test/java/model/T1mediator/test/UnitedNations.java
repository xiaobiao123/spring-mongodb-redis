package model.T1mediator.test;

/**
 * Created by gwb on 2017/6/7.
 * 联合国
 */
abstract class UnitedNations {
    private Country usa;
    private Country iraq;

    public void setUsa(Country usa) {
        this.usa = usa;
    }

    public void setIraq(Country iraq) {
        this.iraq = iraq;
    }

    public Country getUsa() {
        return usa;
    }

    public Country getIraq() {
        return iraq;
    }

    public abstract void declare(String message, Country country);
}
