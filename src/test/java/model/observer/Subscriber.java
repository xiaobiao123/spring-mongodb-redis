package model.observer;

//订阅者
class Subscriber implements Observer {
    private double currentPrice;
    //观察者引用主题  
    private Subject product;

    public Subscriber(Subject product) {
        this.product = product;
        //主题注册观察者
        product.registerObserver(this);
    }

    public void update(double price) {
        this.currentPrice = price;
        System.out.println("Current price change to:" + currentPrice);
    }
}  