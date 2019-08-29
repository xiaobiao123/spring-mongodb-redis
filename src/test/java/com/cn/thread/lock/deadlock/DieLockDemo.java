package com.cn.thread.lock.deadlock;

/*
 * 同步的弊端：
 *         A:效率低
 *         B:容易产生死锁
 * 
 * 死锁：
 *         两个或两个以上的线程在争夺资源的过程中，发生的一种相互等待的现象。
 * 
 * 举例：
 *         小明和小强的自行车都有两把锁一人一把钥匙案例。
 *         正常情况：
 *             小明: 两把锁的钥匙都有;
 *             小强: 两把锁的钥匙都有。
 *         现在：
 *             小明：有其中一把锁的两把钥匙；
 *             小强：有另一把锁的两把钥匙。
 *             结局两个人都不能打开锁。。。。一直等待朔夜起不到自行车
 */
public class DieLockDemo {
    public static void main(String[] args) {
        DieLock dl1 = new DieLock(true);
        DieLock dl2 = new DieLock(false);

        dl1.start();
        dl2.start();
    }
}