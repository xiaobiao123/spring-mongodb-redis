package com.cn.Proxy.cglibEnhancer;

public class TestCgibl {
    public static void main(String[] args) {
        //创建我们的代理类
        ShipProxy proxy = new ShipProxy();
        Ship ship = (Ship) proxy.getProxy(Ship.class);
        ship.travel();

        TestC testC=new TestC();

        Ship proxy2 = (Ship)testC.getProxy(Ship.class);

        proxy2.travel();

    }

}