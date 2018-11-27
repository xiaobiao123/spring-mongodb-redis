package model.A1singleton.sing;

/**
 * Created by gwb on 2018/6/12.
 * 单列模式的七种写法
 * 1、懒汉式（线程安全）：这种写法能够在多线程中很好的工作，
 * 但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，
 * 而且大部分时候我们是用不到同步的，所以不建议用这种模式。
 */
public class Singleton13 {

    private static Singleton13 instance;

    private Singleton13() {

    }

    public static synchronized Singleton13 getSingleton11() {
        if (instance == null) {
            return new Singleton13();
        }
        return instance;
    }

}
