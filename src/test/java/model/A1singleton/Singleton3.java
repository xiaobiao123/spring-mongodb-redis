package model.A1singleton;

public class Singleton3 {
    /**
     * 静态内部类单例模式---延迟加载
     * <p>
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static Singleton3 instance = new Singleton3();
    }

    /**
     * 私有化构造方法
     */
    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.instance;
    }

}