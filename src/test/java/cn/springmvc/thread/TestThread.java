package cn.springmvc.thread;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread extends Thread {
    private int threadnum;

    public TestThread(int threadnum) {
        this.threadnum = threadnum;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TestThread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NO." + threadnum + ":" + i);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1; i++) {
            //TestThread testThread = new TestThread(i);
            //testThread.start();
            //testThread.interrupt();//判断线程是否处于休眠状态，如果是则抛出异常
            //
            ////****************是否要清除状态位****************//
            //System.out.println("========================" + testThread.isInterrupted());
            //testThread.isInterrupted(); //isInterrupted(false) 查看中断位状态 只是简单的查询中断状态
            //testThread.interrupted();
            //System.out.println("**************" + testThread.isInterrupted());
            ////testThread.interrupted(); //currentThread().isInterrupted(true); 清楚中断位
            //
            ////TestThread.class.wait();
            executorService.execute(new TestThread(i));
        }
        executorService.shutdown();

    }
}  