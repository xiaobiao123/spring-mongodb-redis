package model.C3state;

/**
 * State状态模式和Proxy代理模式都为客户端程序提供了一个目标程序代理，真正的目标程序被代理所隐藏，
 * 当客户端程序调用目标程序时，首先将调用请求发送给代理，代理才真正调用目标程序，但是Proxy代理模式
 * 和State状态模式有如下区别：
 * (1).Proxy代理模式中被调用的目标程序只有一个，而State状态模式中被调用的目标程序有多个。
 * (2).Proxy代理模式的目的是控制客户端对目标程序的访问，而State状态模式是为了根据条件动态改变目标程序。
 */
public class StateDemo {

    private ServiceProvider sp = new ServiceProvider(new Implementation1());

    private void run(ServiceProvider sp) {
        sp.service1();
        //sp.service2();
        //sp.service3();
    }

    public static void main(String[] args) {
        StateDemo demo = new StateDemo();

        demo.run(demo.sp);
        demo.sp.changeState(new Implementation2());
        demo.run(demo.sp);
    }
}  