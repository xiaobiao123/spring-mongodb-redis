package model.observer.observer2;

import java.util.Observable;

/**
 * 在Java的java.util中内置了Observer观察者设计模式，其中：
 * (1).java.util.Observable是主题类，所有继承了该类的类是事件发生的主题，也是被观察的对象。java.util.Observable的常用方法有：
 * a.public void addObserver(Observer o)：为主题添加观察者。
 * b.publicvoid deleteObserver(Observer o)：删除某个观察者。
 * c.publicvoid deleteObservers()：删除主题上的所有观察者。
 * d.publicboolean hasChanged()：测试主题是否改变。
 * e.protectedvoid setChanged()：标记该主题对象已经改变。
 * f.publicvoid notifyObservers()：通知所有观察者对象已经已经改变。
 * (2).java.util.Observer接口是观察者，所有实现了该接口的类都是主题事件的观察者，该接口只有一个方法需要实现：
 * publicvoid update(Observable o,  Object arg)：通知观察者更新已经改变的主题。
 * 使用JDK内置的Observer观察者设计模式，演示电子商务网站商品价格的例子如下：
 */

/**
 * JDK中观察者模式的应用：
 * •java.util.Observer
 * •java.util.Observable
 * •java.util.EventListener
 * •javax.servlet.http.HttpSessionBindingListener
 * •javax.servlet.http.HttpSessionAttributeListener
 * •javax.faces.event.PhaseListener
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Product product = new Product();
        Subscriber subscriber = new Subscriber(product);
        product.setPrice(10.98f);
        product.setPrice(998.15f);
    }
}  