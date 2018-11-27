package model.A1singleton.sing;

/**
 * Created by gwb on 2018/6/12.
 * 单列模式的七种写法
 * 1、饿汉试:加载时就完成了初始化，所以类加载较慢，但获取对象的速度快
 */
public class Singleton11 {

    private static Singleton11 singleton11 = new Singleton11();

    private Singleton11() {

    }

    public static Singleton11 getSingleton11() {
        return singleton11;
    }

}
