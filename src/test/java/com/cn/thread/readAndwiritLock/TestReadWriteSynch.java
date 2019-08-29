package com.cn.thread.readAndwiritLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 假如有多个线程要同时进行读操作的话，先看一下synchronized达到的效果：
 */
public class TestReadWriteSynch {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final TestReadWriteSynch testReadWriteSynch = new TestReadWriteSynch();

        new Thread() {
            public void run() {
                testReadWriteSynch.get(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                testReadWriteSynch.get(Thread.currentThread());
            }
        }.start();

    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }
}