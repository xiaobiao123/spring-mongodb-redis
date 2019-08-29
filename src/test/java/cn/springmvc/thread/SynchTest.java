package cn.springmvc.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchTest {
 
    public static void main(String[] args)  {
        final InsertData insertData = new InsertData();
         
        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();
         
         
        new Thread() {
            public void run() {
				insertData.insert(Thread.currentThread());
            };
        }.start();
    }  
}
 
//class InsertData {
//    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
//     
//    public synchronized void insert(Thread com.cn.thread){
//        for(int i=0;i<5;i++){
//            System.out.println(com.cn.thread.getName()+"在插入数据"+i);
//            arrayList.add(i);
//        }
//    }
//}

//class InsertData {
//    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
//     
//    public void insert(Thread com.cn.thread){
//        synchronized (this) {
//            for(int i=0;i<100;i++){
//                System.out.println(com.cn.thread.getName()+"在插入数据"+i);
//                arrayList.add(i);
//            }
//        }
//    }
//}

class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Object object = new Object();
    public void insert(Thread thread){
        synchronized (object) {
            for(int i=0;i<100;i++){
                System.out.println(thread.getName()+"在插入数据"+i);
                arrayList.add(i);
            }
        }
    }
}