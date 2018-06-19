package model.A1singleton.sing;

/**
 * Created by gwb on 2018/6/12.
 * 单列模式的七种写法
 * 1、静态内部类单例模式：不仅能确保线程安全也能保证Singleton类的唯一性，
 * 所以推荐使用静态内部类单例模式。
 */
public class Singleton15 {

    private Singleton15() {

    }

    private static class Sholder {
        private static final Singleton15 instanc = new Singleton15();

    }

    public static Singleton15 getSingleton11() {
        return Sholder.instanc;
    }


}
