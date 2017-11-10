package model.Q1observer;

//主题
interface Subject {
    //注册观察者  
    public void registerObserver(Observer o);

    //注销观察者  
    public void unregisterObserver(Observer o);

    //通知观察者  
    public void notifyObservers();

    void setPrice(double v);
}  