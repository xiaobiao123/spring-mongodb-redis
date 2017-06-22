package model.factory.method.test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class CareBFactory extends ICareFactor {
    @Override
    public ICare createCare() {
        System.out.println("care b..............");
        return new CareB();
    }
}
