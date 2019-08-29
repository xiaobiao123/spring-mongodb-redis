package com.cn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，
 * 则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()
 * 想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()
 * 方法能够中断线程B的等待过程。
 * riː'entrən
 */
public class TestLockInterruptibly {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        TestLockInterruptibly testLockInterruptibly = new TestLockInterruptibly();
        MyThread thread1 = new MyThread(testLockInterruptibly);
        MyThread thread2 = new MyThread(testLockInterruptibly);
        thread1.start();
        thread2.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }
}

class MyThread extends Thread {
    private TestLockInterruptibly testLockInterruptibly = null;

    public MyThread(TestLockInterruptibly testLockInterruptibly) {
        this.testLockInterruptibly = testLockInterruptibly;
    }

    @Override
    public void run() {

        try {
            testLockInterruptibly.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
    }
}