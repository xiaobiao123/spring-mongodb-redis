package thread.queue;

import java.util.concurrent.*;

/**
 * 同步移交队列:是一个没有数据缓冲的BlockingQueue
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {

        ////SynchronousQueue 是一个没有数据缓冲的BlockingQueue
        //Executors.newCachedThreadPool();
        ////DelayedWorkQueue 默认Integer.MAX_VALUE
        ////DelayedWorkQueue 中的元素第一个元素永远是 delay 时间最小的那个元素，如果 delay 没有到期，take 的时候便会 block 住
        ////创建一个定长线程池，支持定时及周期性任务执行
        //Executors.newScheduledThreadPool(10);
        ////LinkedBlockingQueue
        ////创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
        //Executors.newSingleThreadExecutor();
        ////LinkedBlockingQueue  默认Integer.MAX_VALUE
        ////newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        //Executors.newFixedThreadPool(10);
        ////创建一个拥有多个任务队列（以便减少连接数）的线程池
        //Executors.newWorkStealingPool(10);


        //同步移交
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        //offer方法用来向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，
                        // 如果还没有插入成功，则返回false；否则返	回true；
                        //synchronousQueue.offer("xxxxxx");
                        //put方法用来向队尾存入元素，如果队列满，则等待；
                        synchronousQueue.put("xxxxxxxxxxx");
                        System.out.println("放入一个元素");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        //poll方法用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
                        //synchronousQueue.poll();
                        //　take方法用来从队首取元素，如果队列为空，则等待；
                        synchronousQueue.take();
                        System.out.println("获取一个元素");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }

            }
        }).start();


        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(100,true);


        LinkedBlockingQueue linkedBlockingQueue=new LinkedBlockingQueue(100);


        PriorityBlockingQueue<User> queue=new PriorityBlockingQueue(100);

        queue.add(new User(1,"wu"));
        queue.add(new User(5,"wu5"));
        queue.add(new User(23,"wu23"));
        queue.add(new User(55,"wu55"));
        queue.add(new User(9,"wu9"));
        queue.add(new User(3,"wu3"));
        for (User user : queue) {
            try {
                System.out.println(queue.take().name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    //静态内部类
    static class User implements Comparable<User>{

        public User(int age,String name) {
            this.age = age;
            this.name = name;
        }

        int age;
        String name;

        @Override
        public int compareTo(User o) {
            return this.age > o.age ? -1 : 1;
        }
    }

}


