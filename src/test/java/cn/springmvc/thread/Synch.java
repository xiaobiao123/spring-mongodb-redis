package cn.springmvc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Synch {
public static void main(String[] args) {
	
	ExecutorService executorService = Executors.newFixedThreadPool(5);

	for ( int i=0 ; i < 100; i++) {
		
		executorService.submit(new Runnable() {
			public void run() {
				synchronized (this) {
					System.out.println("sssssssssssssssssss"+Thread.currentThread().getId());
				}
				
			}
		});
	}
	executorService.shutdown();
	try {
		new Thread().sleep(1000000);
	} catch (InterruptedException e) {
	System.out.println("xxxxxxxxx");	
	}
}
}
