package model.B2proxy.动态代理;

class FooImpl implements Foo {
    public void f(String s) {
        System.out.println("FooImpl.f(), s=" + s);
    }

    public void g(int i) {
        System.out.println("FooImpl.g(), i=" + i);
    }

    public void h(int i, String s) {
        System.out.println("FooImpl.h(), i=" + i + ", s=" + s);
    }
} 