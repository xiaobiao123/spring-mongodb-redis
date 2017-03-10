package cn.springmvc.thread.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListDemo {
	/**
	 * 读线程
	 * @author wangjie
	 *
	 */
	private static class ReadTask implements Runnable {
		CopyOnWriteArrayList<String> list;

		public ReadTask(CopyOnWriteArrayList<String> list) {
			this.list = list;
		}

		public void run() {
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
	/**
	 * 写线程
	 * @author wangjie
	 *
	 */
	private static class WriteTask implements Runnable {
		CopyOnWriteArrayList<String> list;
		int index;

		public WriteTask(CopyOnWriteArrayList<String> list, int index) {
			this.list = list;
			this.index = index;
		}

		public void run() {
			list.remove(index);
			list.add(index, "write_" + index);
		}
	}

	public void run() {
		final int NUM = 2;
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < NUM; i++) {
			list.add("main_" + i);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(NUM);
		for (int i = 0; i < 2; i++) {
			executorService.execute(new ReadTask(list));
			executorService.execute(new WriteTask(list,0));
		}
		executorService.shutdown();
	}

	public static void main(String[] args) {
		new CopyOnWriteArrayListDemo().run();
	}
}