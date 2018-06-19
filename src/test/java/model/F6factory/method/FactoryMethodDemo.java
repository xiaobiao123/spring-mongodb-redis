package model.F6factory.method;

/**
 * 工厂方法中也只包含一个抽象产品类，抽象产品类可以派生出多个具体产品类
 * <p>
 * 工厂方法定义一个用于创建产品的接口，让子类决定实例化哪一个类，使得类的实例化延迟到子类。
 */
public class FactoryMethodDemo {
    public static void main(String[] args) {
        CarFactory factory = new BenzCarFactory();
        ICar car = factory.createCar();
        car.run();

        factory = new BMWCarFactory();
        car = factory.createCar();
        car.run();
    }
}  