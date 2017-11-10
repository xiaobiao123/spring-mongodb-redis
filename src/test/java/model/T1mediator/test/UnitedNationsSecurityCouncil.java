package model.T1mediator.test;

/**
 * Created by gwb on 2017/6/7.
 */
public class UnitedNationsSecurityCouncil extends UnitedNations {

    @Override
    public void declare(String message, Country country) {
    if (country.equals(getUsa())){
        getIraq().getDeclare(message);
    }else {
        getUsa().getDeclare(message);
    }
    }
}
