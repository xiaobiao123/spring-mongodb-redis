package model.Q1observer;

import java.util.ArrayList;
import java.util.List;

//商品
class Product implements Subject {
    //观察者列表  
    private List<Observer> observers = new ArrayList<Observer>();
    private double price;

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            //主题向观察者推送更新数据
            observer.update(price);
        }
    }

    public void priceChanged() {
        notifyObservers();
    }

    public void setPrice(double price) {
        this.price = price;
        priceChanged();
    }
}  