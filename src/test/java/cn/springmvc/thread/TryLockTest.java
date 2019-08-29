package cn.springmvc.thread;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock(); // 注意这个地方
    static CountDownLatch downLatch = new CountDownLatch(100);

    public static void main(String[] args) {
        TryLockTest tryLockTest = new TryLockTest();

        new Thread() {
            public void run() {
                tryLockTest.insert(Thread.currentThread());
            }
        }.start();

        for (int i = 0; i < 100; i++) {
            downLatch.countDown();
            new Thread() {
                public void run() {
                    tryLockTest.insert(Thread.currentThread());
                }
            }.start();
        }

    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
}
