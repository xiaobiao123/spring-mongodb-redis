package com.cn.thread.pool;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {

        //corePoolSize- 池中所保存的线程数，包括空闲线程。需要注意的是在初创建线程池时线程不会立即启动，直到有任务提交才开始启动线程并逐渐时线程数目达到corePoolSize
        // 。若想一开始就创建所有核心线程需调用prestartAllCoreThreads方法。
        //maximumPoolSize-池中允许的最大线程数。需要注意的是当核心线程满且阻塞队列也满时才会判断当前线程数是否小于最大线程数，并决定是否创建新线程。
        //keepAliveTime - 当线程数大于核心时，多于的空闲线程最多存活时间
        //unit - keepAliveTime 参数的时间单位。
        //workQueue - 当线程数目超过核心线程数时用于保存任务的队列。主要有3种类型的BlockingQueue
        // 可供选择：无界队列，有界队列和同步移交。将在下文中详细阐述。从参数中可以看到，此队列仅保存实现Runnable接口的任务。
        //threadFactory - 执行程序创建新线程时使用的工厂。
        //handler - 阻塞队列已满且线程数达到最大值时所采取的饱和策略。java默认提供了4种饱和策略的实现方式：中止、抛弃、抛弃最旧的、调用者运行。将在下文中详细阐述。

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }

        System.out.println("所有请求已提交");
        executor.shutdown();


        //SynchronousQueue 是一个没有数据缓冲的BlockingQueue
        Executors.newCachedThreadPool();
        //DelayedWorkQueue 默认Integer.MAX_VALUE
        //DelayedWorkQueue 中的元素第一个元素永远是 delay 时间最小的那个元素，如果 delay 没有到期，take 的时候便会 block 住
        //创建一个定长线程池，支持定时及周期性任务执行
        Executors.newScheduledThreadPool(10);
        //LinkedBlockingQueue
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
        Executors.newSingleThreadExecutor();
        //LinkedBlockingQueue  默认Integer.MAX_VALUE
        //newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        Executors.newFixedThreadPool(10);
        //创建一个拥有多个任务队列（以便减少连接数）的线程池
        //Executors.newWorkStealingPool(10);


    }


}


class MyTask<Integer> implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hellow " + taskNum + "执行完毕");
    }
}