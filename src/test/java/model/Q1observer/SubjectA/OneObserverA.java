package model.Q1observer.SubjectA;

/**
 * Created by gwb on 2018/6/15.
 */
public class OneObserverA implements ObserverA {
    @Override
    public void update() {
        System.out.println("this is one observer a update");
    }
}
