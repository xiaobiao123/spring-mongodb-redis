package model.B2proxy.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理处理类
class ProxyHandler implements InvocationHandler {
    //代理实现类  
    private Object delegate;

    public ProxyHandler(Object obj) {
        delegate = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("Before mothod:" + method);
        method.invoke(proxy, args);
        System.out.println("After mothod:" + method);
        return null;
    }

    public Object getProxy(Object object) {
        return Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), object.getClass().getInterfaces(), this);
    }
} 