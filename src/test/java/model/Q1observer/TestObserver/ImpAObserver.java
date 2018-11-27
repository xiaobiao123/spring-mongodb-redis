package model.Q1observer.TestObserver;

/**
 * Created by gwb on 2018-09-20.
 */
public class ImpAObserver implements AObserver {
    @Override
    public void changePrice(double price) {
        System.out.println(price);
    }
}
