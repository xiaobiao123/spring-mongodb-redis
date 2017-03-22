package thread.readAndwiritLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 过要注意的是，如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁
 * ，则申请写锁的线程会一直等待释放读锁。
 　　如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。
 */
public class TestReadWrite {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final TestReadWrite testReadWrite = new TestReadWrite();
         
        new Thread(){
            public void run() {
                testReadWrite.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                testReadWrite.get(Thread.currentThread());
            };
        }.start();
         
    }  
     
    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}