package model.Q1observer.TestObserver;

/**
 * Created by gwb on 2018-09-20.
 */
public interface Asubject {
    //注册观察者
    public void registerObserver(AObserver aObserver);


    //注销观察者
    public void unregisterObserver(AObserver observer);

    //通知观察者
    public void notifyObserver();

    void setPrice(double v);
}
