package model.F6factory.simple;

/**
 * 定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类。
 * 因为在简单工厂模式中用于创建实例的方法是静态(static)方法，因此简单工厂模式又被称为静态
 * 工厂方法(Static Factory Method)模式，它属于类创建型模式
 */
public class Main {
    public static void main(String[] args) {
        ICode code = FactoryCreater.getCodingSkill("php");
        code.coding();
        code = FactoryCreater.getCodingSkill("android");
        code.coding();
        code = FactoryCreater.getCodingSkill("ios");
        code.coding();
    }
}