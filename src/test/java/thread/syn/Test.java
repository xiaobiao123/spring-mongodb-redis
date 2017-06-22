package thread.syn;

/**
 * 并且如果一个线程执行一个对象的非static synchronized方法，另外一个线程需要执行这个对
 * 象所属类的static synchronized方法，此时不会发生互斥现象，因为访问static synchronized
 * 方法占用的是类锁，而访问非static synchronized方法占用的是对象锁，所以不存在互斥现象
 */
public class Test {
 //对于synchronized方法或者synchronized代码块，当出现异常时，
 // JVM会自动释放当前线程占用的锁，因此不会由于异常导致出现死锁现象。
    public static void main(String[] args)  {
        final InsertData insertData = new InsertData();
        new Thread(){
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                insertData.insert1();
            }
        }.start();
    }  
}
 
class InsertData { 
    public synchronized void insert(){
        System.out.println("执行insert");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }
     
    public synchronized static void insert1() {
        System.out.println("执行insert1");
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert1完毕");
    }
}