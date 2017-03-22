package thread.thread;

import java.io.IOException;

/**
 * 假如在main线程中，调用thread.join方法，则main方法会等待thread线程执行完毕或者等待一定的时间。
 * 如果调用的是无参join方法，则等待thread执行完毕，如果调用的是指定了时间参数的join方法，则等待一定的事件
 */
public class TestJoin {
     
    public static void main(String[] args) throws IOException {
        System.out.println("进入线程"+Thread.currentThread().getName());
        TestJoin testJoin = new TestJoin();
        MyThread thread1 = testJoin.new MyThread();
        thread1.start();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"等待");
            thread1.join();
            System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
     
    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("进入线程"+Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
        }
    }
}