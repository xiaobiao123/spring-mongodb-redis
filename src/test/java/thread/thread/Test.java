package thread.thread;

import java.io.IOException;

public class Test {
     
    public static void main(String[] args) throws IOException {
        Test test = new Test();
        MyThread2 thread = test.new MyThread2();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }
     
    class MyThread2 extends Thread{
        @Override
        public void run() {
            int i = 0;
            while(i<Integer.MAX_VALUE){
                System.out.println(i+" while循环");
                i++;
            }
        }
    }
}