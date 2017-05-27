package apo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;

public class DynaProxyHello implements InvocationHandler {
    private Object target;//目标对象

    /**
     * 通过反射来实例化目标对象
     *
     * @param object
     * @return
     */
    public Object bind(Object object) {
        this.target = object;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        Logger.start();//添加额外的方法
        //通过反射机制来运行目标对象的方法
        result = method.invoke(this.target, args);
        Logger.end();
        return result;
    }
}