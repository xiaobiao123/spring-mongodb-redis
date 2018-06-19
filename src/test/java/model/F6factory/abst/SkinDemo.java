package model.F6factory.abst;


/**
 * 抽象工厂模式中可以包括多个抽象产品类，每个抽象产品类可以产生出多个具体产品类，
 * 一个抽象工厂用于定义所需产品的组合形式，抽象工厂派生具体工厂类，这些具体工
 * 厂类就是简单工厂模式中的工厂类，具体工厂类负责具体产品实例的创建
 * <p>
 * 为创建一组相关或相互依赖的对象提供一个接口，而且无须指定它们
 * 的具体类
 */
public class SkinDemo {
    public static void main(String[] args) {
        //显示一套IOS皮肤  
        Skin skin = new Skin(new IOSSkinFactory());
        skin.showSkin();
        //换一套Android的皮肤
        skin.setSkinFactory(new AndroidSkinFactory());
        skin.showSkin();
    }
}  