package com.cn.thread.readAndwiritLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by gwb on 2017/3/30.
 */
public class Test {

    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    public static void main(String[] args) {

       final Test test =new Test();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

    }

    public void get(Thread thread){
        lock.readLock().lock();

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <100 ; i++) {
            System.out.println(thread.getName()+"正在进行读操作");
        }

        System.out.println(thread.getName()+"读操作完毕");
    }

}
