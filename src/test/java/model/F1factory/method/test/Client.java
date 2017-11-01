package model.F1factory.method.test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class Client {
    public static void main(String[] args) {

        ICareFactor factor = new CareAFactory();

        ICare care = factor.createCare();

        ICareFactor factorB = new CareBFactory();

        ICare careB = factorB.createCare();


    }
}
