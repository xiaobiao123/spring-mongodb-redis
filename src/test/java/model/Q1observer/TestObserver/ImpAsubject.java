package model.Q1observer.TestObserver;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by gwb on 2018-09-20.
 */
public class ImpAsubject implements Asubject {

    List<AObserver> list = Lists.newArrayList();
    private double price;

    @Override
    public void registerObserver(AObserver aObserver) {
    list.add(aObserver);
    }

    @Override
    public void unregisterObserver(AObserver observer) {
        list.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (AObserver observer:list){
            observer.changePrice(price);
        }
    }

    @Override
    public void setPrice(double v) {
        this.price=v;
        notifyObserver();
    }
}
