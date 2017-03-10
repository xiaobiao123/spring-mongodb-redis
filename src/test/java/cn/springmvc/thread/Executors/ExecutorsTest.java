package cn.springmvc.thread.Executors;
 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
/**
 * 单服务器用线程池实现秒杀的思路一
 * 
 * 
 */
public class ExecutorsTest {
 
    public static boolean flag = true; // 秒杀物品的标记
 
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 3, 0L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
        ThreadTest t1 = new ThreadTest("张三");
        ThreadTest t2 = new ThreadTest("李四");
        ThreadTest t3 = new ThreadTest("王五");
        try {
            pool.execute(t1);
        } catch (Exception e) {
            System.out.println(t1.getUserName() + "没有抢到");
        }
 
        try {
            pool.execute(t3);
        } catch (Exception e) {
            System.out.println(t3.getUserName() + "没有抢到");
        }
 
        try {
            pool.execute(t2);
        } catch (Exception e) {
            System.out.println(t2.getUserName() + "没有抢到");
        }
        pool.shutdown();
    }
 
}
 
class ThreadTest extends Thread {
 
    private String userName;
 
    public ThreadTest(String userName) {
        super();
        this.userName = userName;
    }
 
    @Override
    public void run() {
        try {
            Thread.sleep(200);
            if (ExecutorsTest.flag) {
                System.out.println(this.userName + "秒杀成功");
                ExecutorsTest.flag = false;
            }
 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
}
