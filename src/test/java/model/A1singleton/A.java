package model.A1singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by gwb on 2018/6/12.
 */
public class A {

    private static final A a = null;

    //私有属性
    private String name;

    //私有方法
    private void say(String s) {
        System.out.println("qq" + "我是" + s + "啊");
    }

    //公共属性
    public int age;

    //公共方法
    public void luru(String name) {
        System.out.println("qq" + "我是" + name);
    }


    private A() {
        System.out.println("qq" + "我是" + "xxxxxxxxxxxxxxxx");
    }

    private A(String name) {
        System.out.println("qq" + "我是" + name);
    }


    public static A getA() {
        if (a == null) {
            return new A();
        } else {
            return a;
        }
    }


    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("model.A1singleton.A");
            Object o = aClass.newInstance();

            //获取私有属性
            Field name = aClass.getDeclaredField("name");
            name.setAccessible(true);
            name.set(o, "我是私有属性xxxxxxxxxxxxxxxx");
            System.out.println("qq" + "我设置的name：" + name.get(o));


            //获取私有方法
            Method say = aClass.getDeclaredMethod("say", new Class[]{String.class});
            say.setAccessible(true);
            say.invoke(o, "私有方法");


            //获取公共属性
            Field age = aClass.getField("age");
            age.setAccessible(true);
            age.set(o, 11);
            System.out.println("qq" + "我设置的age：" + age.get(o));
            //获取公共方法
            Method luru = aClass.getMethod("luru", new Class[]{String.class});
            luru.setAccessible(true);
            luru.invoke(o, "公共方法");


            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();

            for (Constructor method : declaredConstructors) {
                method.setAccessible(true);
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes==null ||parameterTypes.length==0){
                    Object o1 = method.newInstance();
                }
                System.out.println(method.getName() + "============" + method.isAccessible());

                method.setAccessible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
