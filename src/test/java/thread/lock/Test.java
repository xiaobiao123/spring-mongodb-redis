package thread.lock;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/4/1.
 */
public class Test {
    private ArrayList list = Lists.newArrayList();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
      final   Test test = new Test();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        };

        thread1.start();

        System.out.println("main:" + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        };

        thread.start();
        System.out.println("main2:" + Thread.currentThread().getName());
    }

    public void insert(Thread thread) {
        lock.readLock().lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("thread:" + thread.getName() + ":" + thread.getId());
            }
            Thread.sleep(4000);
            System.out.println("执行结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }


    }
}
