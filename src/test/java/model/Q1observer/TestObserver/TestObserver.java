package model.Q1observer.TestObserver;

/**
 * Created by gwb on 2018-09-20.
 */
public class TestObserver {
    public static void main(String[] args) {
        Asubject subject=new ImpAsubject();

        AObserver observer=new ImpAObserver();
        AObserver observer2=new ImpAObserver();


        subject.registerObserver(observer);
        subject.registerObserver(observer2);


        subject.setPrice(1.09);
        subject.setPrice(2.09);
        subject.setPrice(3.09);

    }
}
