package cn.springmvc.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by gwb on 2018/5/29.
 */
public class TestStudy {

    /**
     * ConcurrentHashMap
     */
    @Test
    public void testConcurrentHashMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("No1", "value1");
        concurrentHashMap.put("No2", "value2");
        concurrentHashMap.remove("No1");
        concurrentHashMap.put("No3", "value3");
        concurrentHashMap.put("No4", "value4");

    }

    /**
     * 1.非阻塞队列
     * add(E e)、remove() 成功true，失败抛出异常
     * <p>
     * offer(E e)插入：成功true，失败false
     * <p>
     * poll() 移除并获取队首元素，成功true，失败null
     * <p>-+
     * peek()：获取队首元素，失败null;
     */
    @Test
    public void testQueue() {

    }

    /**
     * 1.阻塞队列
     * add(E e)、remove() 成功true，失败抛出异常
     * <p>
     * offer(E e)插入等待时间：成功true，失败false
     * <p>
     * poll() 移除并获取队首元素，成功true，失败null
     * <p>
     * peek()：获取队首元素，失败null;
     * <p>
     * ********
     * put方法用来向队尾存入元素，如果队列满，则等待；
     * take方法用来从队首取元素，如果队列为空，则等待；
     */
    @Test
    public void testBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(2);
        System.out.println("this is poll " + arrayBlockingQueue.poll());

        arrayBlockingQueue.put("22");
        arrayBlockingQueue.take();
        System.out.println("this is take");
        arrayBlockingQueue.put("22");
        arrayBlockingQueue.put("22");
        System.out.println("this is offer" + arrayBlockingQueue.offer("xxx"));


        SynchronousQueue synchronousQueue=new SynchronousQueue();
    }
}
