package model.A1singleton.sing;

/**
 * Created by gwb on 2018/6/12.
 * 单列模式的七种写法
 * 1、懒汉式（线程不安全）：懒汉模式申明了一个静态对象，在用户第一次调用时初始化，虽然节约了资源，
 * 但第一次加载时需要实例化，反映稍慢一些，而且在多线程不能正常工作。
 */
public class Singleton12 {

    private static Singleton12 instance;

    private Singleton12() {

    }

    public static Singleton12 getSingleton11() {
        if (instance == null) {
            return new Singleton12();
        }
        return instance;
    }

}
