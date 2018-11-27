package model.A1singleton.sing;

/**
 * Created by gwb on 2018/6/12.
 * 单列模式的七种写法
 * 1、双重检查模式 （DCL）：DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。
 * 缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷，虽然发生的概率很小
 */
public class Singleton14 {

    //**************** volatile
    private volatile static Singleton14 instance;

    private Singleton14() {

    }

    public static Singleton14 getSingleton14() {
        if (instance == null) {
            synchronized (Singleton14.class) {
                if (instance == null) {
                    return new Singleton14();
                }
            }

        }
        return instance;
    }

}
