package thread.thread;

import java.io.IOException;

/**
 * sleep方法不会释放锁，也就是说如果当前线程持有对某个对象的锁，则即使调用sleep方法
 * ，其他线程也无法访问这个对象。看下面这个例子就清楚了：
 */
public class TestSleep {

    private int i = 10;
    private Object object = new Object();

    public static void main(String[] args) throws IOException {

        System.out.println("main=====" + Thread.currentThread().getId());
        TestSleep testSleep = new TestSleep();
        MyThread thread1 = testSleep.new MyThread();
        MyThread thread2 = testSleep.new MyThread();
        thread1.start();
        System.out.println("thread1=====" + thread1.getId());
        thread2.start();
        System.out.println("thread2=====" + thread2.getId());
        Thread thread = new Thread(new BtestRunnable());
        thread.start();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:" + i);
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                i++;
                System.out.println("i:" + i);
            }
        }
    }

    static class BtestRunnable implements Runnable {

        @Override
        public void run() {

            System.out.println("xxxxxxxxxxxxxxxx");
        }
    }


}