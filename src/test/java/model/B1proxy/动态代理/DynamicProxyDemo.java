package model.B1proxy.动态代理;

import java.lang.reflect.Proxy;

/**
 * Proxy代理设计模式是一种控制对象访问的设计模式
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        Foo foo = new FooImpl();
        ProxyHandler handler = new ProxyHandler(foo);
        //产生动态代理    类对象的加载器
        Foo proxy = (Foo) Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), new Class[]{Foo.class}, handler);
        proxy.f("f");
        //proxy.g(1);
        //proxy.h(1,"xxxxxx");
    }
}  