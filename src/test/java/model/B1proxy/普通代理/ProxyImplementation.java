package model.B1proxy.普通代理;

//目标程序
class ProxyImplementation implements ProxyBase {
    public void f() {
        System.out.println("ProxyImplementation.f()");
    }

    public void g() {
        System.out.println("ProxyImplementation.g()");
    }

    public void h() {
        System.out.println("ProxyImplementation.h()");
    }
}