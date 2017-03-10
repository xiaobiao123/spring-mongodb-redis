package cn.springmvc.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	volatile int i=0;
	
	public static void main(String[] args) {
		final ReadWriteLockTest test = new ReadWriteLockTest();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();

	}

	public  void get(Thread thread) {
		rwl.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName() + "正在进行读操作");
			}
			System.out.println(thread.getName() + "读操作完毕");
		} catch (Exception e) {
		}finally {
			rwl.readLock().unlock();
		}
	}
}
