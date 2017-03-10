package cn.springmvc.thread.threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.springmvc.model.User;

public class ThreadLocalInitTest {
	
	
	ThreadLocal<String> th=new ThreadLocal<String>(){
		protected String initialValue(){
			return Thread.currentThread().getName();
		}
	};

	public String getTh(){
		return th.get();
	}
	
	ThreadLocal<Long> long1 = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return Thread.currentThread().getId();
		};
	};

	ThreadLocal<String> ss = new ThreadLocal<String>() {
		protected String initialValue() {
			return Thread.currentThread().getName();
		}

	};

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return Thread.currentThread().getId();
		};
	};
	ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
		protected String initialValue() {
			return Thread.currentThread().getName();
		};
	};

	// public void set() {
	// longLocal.set(Thread.currentThread().getId());
	// stringLocal.set(Thread.currentThread().getName());
	// }

	public String getSS() {
		return ss.get();
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalInitTest test = new ThreadLocalInitTest();

		// test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		System.out.println(test.getSS());

		Thread thread1 = new Thread() {
			public void run() {
				// test.set();
				System.out.println("thread1..........."+test.getLong());
				System.out.println("thread1.............."+test.getString());
			};
		};
		thread1.start();

		Thread thread2 = new Thread() {
			public void run() {
				// test.set();
				System.out.println("thread2..........."+test.getLong());
				System.out.println("thread2............"+test.getString());
			};
		};
		thread2.start();
		// thread1.join();

		System.out.println("main..........."+test.getLong());
		System.out.println("main..........."+test.getString());

	}

	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		public Connection initialValue() {
			try {
				return DriverManager.getConnection("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	public static Connection getConnection() {
		return connectionHolder.get();
	}
}