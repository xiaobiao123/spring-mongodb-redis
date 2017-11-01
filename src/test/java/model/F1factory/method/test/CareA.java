package model.F1factory.method.test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class CareA extends ICare {
    @Override
    public ICare creatCare() {
        System.out.println("carea.................");

        return new CareA();
    }
}
