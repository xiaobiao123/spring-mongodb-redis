package com.cn.thread.thread;

import java.io.IOException;

public class Test {
    static class MyThread2 extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 100) {
                System.out.println(i + " while循环");
                i++;
            }
        }
    }

    static class MyRunner implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunner is running");
        }
    }

    static class MyRunner3 extends Thread {
        @Override
        public void run() {
            System.out.println("my runner 3 is going");
        }
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        MyThread2 thread = new MyThread2();
        MyRunner myRunner = new MyRunner();
        MyRunner3 myRunner3 = new MyRunner3();

        Thread t = new Thread(myRunner);
        thread.start();
        t.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();


        myRunner3.start();
    }
}