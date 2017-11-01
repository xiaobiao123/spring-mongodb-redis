package model.B1proxy.普通代理;

class Proxy implements ProxyBase {

    private ProxyBase implementation;

    public Proxy() {
        //目标程序
        implementation = new ProxyImplementation();
    }

    public void f() {
        implementation.f();
    }

    public void g() {
        implementation.g();
    }

    public void h() {
        implementation.h();
    }
}