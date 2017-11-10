package model.Q1observer.observer2;

import java.util.Observable;

//商品
class Product extends Observable {
    private float price;

    public void priceChanged() {
        //设置变化点
        setChanged();
        //通知观察者
        notifyObservers();
    }

    public void setPrice(float price) {
        this.price = price;
        priceChanged();
    }

    public float getPrice() {
        return price;
    }
}  