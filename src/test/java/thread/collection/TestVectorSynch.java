package thread.collection;

import java.util.Vector;

/**
 * 因此为了保证线程安全，必须在方法调用端做额外的同步措施
 */
public class TestVectorSynch {
    static Vector<Integer> vector = new Vector<Integer>();
    public static void main(String[] args) throws InterruptedException {
        while(true) {
            for(int i=0;i<10;i++)
                vector.add(i);
            Thread thread1 = new Thread(){
                public void run() {
                    synchronized (TestVectorSynch.class) {   //进行额外的同步
                        for(int i=0;i<vector.size();i++)
                            vector.remove(i);
                    }
                };
            };
            Thread thread2 = new Thread(){
                public void run() {
                    synchronized (TestVectorSynch.class) {
                        for(int i=0;i<vector.size();i++)
                            vector.get(i);
                    }
                };
            };
            thread1.start();
            thread2.start();
            while(Thread.activeCount()>10)   {

            }
        }
    }
}