package model.B2proxy.动态代理;

import java.lang.reflect.Proxy;

/**
 * 为其他对象提供一种代理以控制对这个对象的访问
 * <p>
 * Proxy代理设计模式是一种控制对象访问的设计模式
 * <p>
 * 职责清晰：真实的角色就是实现实际的业务逻辑，不用关心其他非本职责的事务，通过后期的代理
 * 完成一件事务，附带的结果就是编程简洁清晰
 * <p>
 * 高扩展性：具体主题角色是随时都会发生变化的，只要它实现了接口，甭管它如何变化，都逃不脱
 * 如来佛的手掌（接口），那我们的代理类完全就可以在不做任何修改的情况下使用。
 * <p>
 * 智能化：
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {

        Foo foo = new FooImpl();

        String className = foo.getClass().getName();
        //if (className.startsWith("model.B2proxy.动态代理")){
        ProxyHandler handler = new ProxyHandler(foo);
        //产生动态代理    类对象的加载器,目标对象实现的接口 ,InvocationHandler的实现类
        //Foo proxy = (Foo) Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), new Class[]{Foo.class}, handler);
        Foo proxy = (Foo) handler.getProxy(foo);
        proxy.f("f");

        //proxy.g(1);
        //proxy.h(1,"xxxxxx");
    }
}  