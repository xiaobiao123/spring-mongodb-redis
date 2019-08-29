package com.cn.thread.lock.deadlock;

public class RunnableDemo {
    public static void main(String[] args) {
        SellTicket str = new SellTicket();
        
        Thread tr1 = new Thread(str, "窗口1");
        Thread tr2 = new Thread(str, "窗口2");
        Thread tr3 = new Thread(str, "窗口3");
        
        //
        tr1.start();
        tr2.start();
        tr3.start();
    }
}