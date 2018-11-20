package Ckeck;

import model.A1singleton.Singleton1;
import org.junit.Test;

/**
 *
 */
public class ClassForNameAndClassLoader {
    /**
     * 类的加载过程---加载-->验证-->准备-->解析--初始化-->使用-->卸载
     *                   -------链接------
     */
    @Test
    public void testClass() {
        /*class.forName()前者除了将类的.class文件加载到jvm中之外，
          还会对类进行解释，执行类中的static块。*/
        try {
            /**boolean参数表示类是否需要初始化
             * Class.forName(className)方法，内部实际调用的方法是  Class.forName(className,true,classloader);
             */
            //Class.forName("Ckeck.ClassA");
            //
            /**
             * boolean参数表示类是否需要初始化
             * ClassLoader.loadClass(className)方法，内部实际调用的方法是
             * ClassLoader.loadClass(className,false);
             */
            //ClassLoader.getSystemClassLoader().loadClass("model.A1singleton.Singleton1");
            this.getClass().getClassLoader().loadClass("Ckeck.ClassA");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
