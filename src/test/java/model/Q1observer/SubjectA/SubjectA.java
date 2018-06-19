package model.Q1observer.SubjectA;

import java.util.ArrayList;

/**
 * Created by gwb on 2018/6/15.
 * <p>
 * 被观察者
 */
abstract class SubjectA {

    private ArrayList<ObserverA> list = new ArrayList<>();

    public void addObserverA(ObserverA observerA) {
        list.add(observerA);
    }


    public void removeObserverA(ObserverA observerA) {
        list.remove(observerA);
    }

    public void notifyObservers() {
        for (ObserverA observerA : list) {
            observerA.update();
        }

    }
}
