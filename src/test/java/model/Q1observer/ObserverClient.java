package model.Q1observer;

/**
 * Observer观察者设计模式用于将对象的变化通知给感兴趣的用户
 * <p>
 * 在Observer模式中的角色为主题（subject）与观察者（Q1observer），观察者订阅它感兴趣的主题，
 * 一个主题可以被多个观 察者订阅，当主题的状态发生变化时，它必须通知（notify）所有订阅它的观察者，
 * 观察者检视主题的状态变化，并作出对应的动作，所以Observer模式也称之为Publish-Subscribe模式
 */
public class ObserverClient {
    public static void main(String[] args) {

        Subject product = new Product();

        Subscriber subscriber = new Subscriber(product);

        product.setPrice(10.98);
        product.setPrice(998.15);
    }
}  