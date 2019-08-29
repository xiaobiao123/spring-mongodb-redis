package com.cn.Proxy.cglibEnhancer;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by gwb on 2018/6/13.
 */
public class TestC implements MethodInterceptor {

    Enhancer enhancer = new Enhancer();

    public Object getProxy(Class c) {
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(object, args);
        return null;
    }

    //class VV implements MethodInterceptor {
    //    Enhancer enhancer = new Enhancer();
    //
    //    public Object getProxy(Class o) {
    //        enhancer.setSuperclass(o);
    //        enhancer.setCallback(this);
    //        return enhancer.create();
    //    }
    //
    //    @Override
    //    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    //        methodProxy.invoke(o, args);
    //        return null;
    //    }
    //}

}
