package model.A1singleton;

/**
 * Created by gwb on 2017/11/9.
 */
public class SinTest {
    public static void main(String[] args) {
        Singleton1 singleton2= Singleton1.geInstance();

        ///*class.forName()前者除了将类的.class文件加载到jvm中之外，
        //  还会对类进行解释，执行类中的static块。*/
        //try {
        //     Class.forName("model.A1singleton.Singleton1");
        //
        //    ClassLoader.getSystemClassLoader().loadClass("model.A1singleton.Singleton1");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

    }
}
