package model.factory.method.test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class CareB extends ICare {
    @Override
    public ICare creatCare() {
        System.out.println("care b");
        return new CareA();
    }
}
