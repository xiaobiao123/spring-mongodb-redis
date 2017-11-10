package model.L1decorator.test2;

/**
 * Created by Administrator on 2017/6/16.
 */
abstract class AbstDecort {
    protected ICom iCom;

    public AbstDecort(ICom iCom) {
        this.iCom = iCom;
    }

    public abstract void decort();
}
