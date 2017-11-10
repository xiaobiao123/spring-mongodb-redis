package model.L1decorator.test2;

/**
 * Created by Administrator on 2017/6/16.
 */
public class DecortA extends AbstDecort {

    public DecortA(ICom iCom) {
        super(iCom);
    }

    @Override
    public void decort() {
        System.out.println("i am is decortA...........");
        iCom.drop();
    }
}
