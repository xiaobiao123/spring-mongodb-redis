package cn.springmvc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Synch {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Executors.newScheduledThreadPool(10);

        Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {

            executorService.submit(new Runnable() {
                public void run() {
                    synchronized (this) {
                        System.out.println("sssssssssssssssssss" + Thread.currentThread().getId());
                    }

                }
            });
        }
        executorService.shutdown();//关闭线程池

        //判断是否所有的线程已经运行完
        while (!executorService.isTerminated()) {

        }
        System.out.println("xxxxxxxxx");
    }
}
