package thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
 * 如果获取失败（即锁已被其他线程获取），
 * 则返回false，也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
 * @param thread
 */

public class TestTryLock {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args) {
        final TestTryLock testTryLock = new TestTryLock();

        new Thread() {
            public void run() {
                testTryLock.insert(Thread.currentThread());
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                testTryLock.insert(Thread.currentThread());
            }

            ;
        }.start();
    }


    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
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