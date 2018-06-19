package model.B2proxy.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//动态代理处理类
class ProxyHandler implements InvocationHandler {
    //代理实现类  
    private Object delegate;

    public ProxyHandler(Object obj) {
        delegate = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("Before mothod:" + method);
        method.invoke(this.delegate, args);
        System.out.println("After mothod:" + method);
        return null;
    }
} 