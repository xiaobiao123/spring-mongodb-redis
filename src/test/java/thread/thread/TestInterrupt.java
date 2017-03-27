package thread.thread;

import java.io.IOException;

/**
 * interrupt方法不能中断正在运行中的线程
 * <p>
 * 　interrupt，顾名思义，即中断的意思。单独调用interrupt方法可以使得处于阻塞状态的线程抛出一个异常
 * ，也就说，它可以用来中断一个正处于阻塞状态的线程；另外，
 * 通过interrupt方法和isInterrupted()方法来停止正在运行的线程。
 */
public class TestInterrupt {

    public static void main(String[] args) throws IOException {
        TestInterrupt testInterrupt = new TestInterrupt();
        MyThread thread = testInterrupt.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {

        }
        /*将线程中断标志设置为true*/
        thread.interrupt();

        /*如果当前线程的中断标志是true,将线程中断标志设置为false*/
        System.out.println("interrupted22=======================:" + thread.interrupted());

        while (!thread.isInterrupted()){

        }
        /*判断当前现在的中断状态*/
        System.out.println("isInterrupted22=======================:" + thread.isInterrupted());
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("进入睡眠状态");
                Thread.currentThread().sleep(4000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                System.out.println("得到中断异常");
//                System.out.println("isInterrupted=======================:" + Thread.currentThread().isInterrupted());
//                System.out.println("interrupted=======================:"+Thread.currentThread().interrupted());
            }
            System.out.println("run方法执行完毕");
        }
    }
}