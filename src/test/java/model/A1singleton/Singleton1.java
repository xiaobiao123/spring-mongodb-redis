package model.A1singleton;

/**
 * 定义：保证一个类仅有一个实例，并提供一个访问他的全局访问点
 * <p>
 * Singleton单类模式是最简单的设计模式，它的主要作用是保证在程序运行生命周期中，使用了单类模式的类只能有一个实例对象存在
 *
 * 优缺点：
 * 1、内存开资小、减小了系统性能开心
 */
public class Singleton1 {
    //饿汉试，声明时就创建实例对象
    public static final Singleton1 instance = new Singleton1();

    //单类模式的构造方法必须为private，以避免通过构造方法创建对象实例，  
    //并且必须显示声明构造方法，以防止使用默认构造方法
    private Singleton1() {
    }

    //单类模式必须对外提供获取实例对象的方法
    public static Singleton1 geInstance() {
        return instance;
    }


}  