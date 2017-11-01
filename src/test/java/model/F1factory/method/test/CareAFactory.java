package model.F1factory.method.test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class CareAFactory extends ICareFactor {
    @Override
    public ICare createCare() {
        System.out.println("care a ..............");
        return new CareA();
    }
}
