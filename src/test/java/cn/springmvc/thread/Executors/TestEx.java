package cn.springmvc.thread.Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.alibaba.fastjson.JSON;

public class TestEx {
	public static void main(String[] args) throws InterruptedException {

		// System.out.println( DateUtil.getAfterMonth(DateUtil.getCurrentDate(),
		// 1));

		// System.out.println(EnterpriseType.getByType("00"));

		 List<String> list=new ArrayList<String>();
		 list.add("test1");
		 list.add("test2");
		 list.add("test3");
		
//		JSONObject json=new JSONObject();
//		json.put("1","1");
//		json.put("1","2");
//		Map map=new HashMap<>();
//		
//		map.put("1","1");
//		map.put("2","2");
		
		
//		 System.out.println(Arrays.asList(map).size());
		//System.out.println(Thread.currentThread().getName());
		 System.out.println(JSON.toJSON(list));


		// Long time = System.currentTimeMillis();
		// ExecutorService executorService = Executors.newFixedThreadPool(8);
		// schedulerFactory2(executorService);
		// schedulerFactory(executorService);
		// executorService.shutdown();
		// Long k = System.currentTimeMillis() - time;
		// System.out.println("..............................." + k);
	}

	private static class ThreadCustomerCert implements Runnable {
		private String str;

		public ThreadCustomerCert(String str) {
			this.str = str;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public synchronized void run() {
			System.out.println("thread.............i=" + str);
		}
	}

	/**
	 * 生产厂商
	 */
	private static void schedulerFactory(ExecutorService executorService) {
		// ExecutorService executorService = Executors.newFixedThreadPool(3);
		// TODO 判断总行书，循环插入
		for (int i = 0; i < 100; i++) {
			executorService.execute(new ThreadCustomerCert("thread+i" + i));
		}
		executorService.shutdown();
	}

	/**
	 * 生产厂商
	 * 
	 * @throws InterruptedException
	 */
	private static void schedulerFactory2(ExecutorService executorService) throws InterruptedException {
		// ExecutorService executorService = Executors.newFixedThreadPool(3);
		// TODO 判断总行书，循环插入
		for (int i = 100; i < 199; i++) {
			executorService.execute(new ThreadCustomerCert("thread+k" + i));
		}

	}
}
