package thread.syn;

import java.util.ArrayList;

/**
 * 1）当一个线程正在访问一个对象的synchronized方法，那么其他线程不能访问该对象的其他synchronized方法。这个原因很简单，
 * 因为一个对象只有一把锁，当一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，所以无法访问该对象的其他synchronized方法。
 * <p>
 * 2）当一个线程正在访问一个对象的synchronized方法，那么其他线程能访问该对象的非synchronized方法。这个原因很简单，
 * 访问非synchronized方法不需要获得该对象的锁，假如一个方法没用synchronized关键字修饰，说明它不会使用到临界资源，
 * 那么其他线程是可以访问这个方法的，
 * <p>
 * 3）如果一个线程A需要访问对象object1的synchronized方法fun1，另外一个线程B需要访问对象object2的synchronized方法fun1，
 * 即使object1和object2是同一类型），也不会产生线程安全问题，因为他们访问的是不同的对象，所以不存在互斥问题。
 */
public class TestSynch {

    public static void main(String[] args) {
        final InsertDataSynch insertData = new InsertDataSynch();

        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                insertData.insert2(Thread.currentThread());
            }
        }.start();

        //        new Thread() {
        //            public void run() {
        //                insertData.insert3(Thread.currentThread());
        //            }
        //        }.start();


    }
}

/**
 * 一个对象只有一把锁
 */
class InsertDataSynch {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    private Object object = new Object();


    /**
     * synchronized在方法上
     *
     * @param thread
     */
    public synchronized void insert(Thread thread) {
        for (int i = 0; i < 5; i++) {
            System.out.println("insert:" + thread.getName() + "在插入数据" + i);
            arrayList.add(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized在方法上
     *
     * @param thread
     */
    public synchronized void insert3(Thread thread) {
        for (int i = 0; i < 5; i++) {
            System.out.println("insert3:" + thread.getName() + "在插入数据" + i);
            arrayList.add(i);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized代码块
     *
     * @param thread
     */
    public void insert2(Thread thread) {

        //        代表获取该属性的锁
        //        synchronized (object) {
        //代表获取当前对象的锁，
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("insert2:" + thread.getName() + "在插入数据" + i);
                arrayList.add(i);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
