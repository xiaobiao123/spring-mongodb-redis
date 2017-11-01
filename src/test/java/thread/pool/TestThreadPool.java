package thread.pool;


import java.util.concurrent.atomic.*;

//测试线程池
public class TestThreadPool {  
    public static void main(String[] args) {  
        // 创建3个线程的线程池  
        ThreadPool t = ThreadPool.getThreadPool(3);  
        t.execute(new Runnable[] { new Task(), new Task(), new Task() });
        t.execute(new Runnable[] { new Task(), new Task(), new Task() });
        System.out.println(t);
        t.destroy();// 所有线程都执行完成才destory
        System.out.println(t);


        //AtomicLong along=new AtomicLong();
        //AtomicInteger ainteger=new AtomicInteger();
        //System.out.println(along.addAndGet(1));


    }  
  
    // 任务类  
    static class Task implements Runnable {  
        private static volatile int i = 1;  
  
        @Override  
        public void run() {// 执行任务  
            System.out.println("任务 " + (i++) + " 完成");  
        }  
    }  
}  