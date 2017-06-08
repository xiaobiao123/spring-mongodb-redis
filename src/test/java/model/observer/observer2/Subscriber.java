package model.observer.observer2;

import java.util.Observable;
import java.util.Observer;

//订阅者
class Subscriber implements Observer {
    private float currentPrice;
    //观察者引用主题  
    private Observable observable;

    public Subscriber(Observable observable) {
        this.observable = observable;
        //主题注册观察者
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Product) {
            Product product = (Product) observable;
            this.currentPrice = product.getPrice();
            System.out.println("Current price change to:" + currentPrice);
        }
    }
}  