package cn.springmvc.thread;
public class TestThread extends Thread{   
    private int threadnum;  
  
    public TestThread(int threadnum) {   
        this.threadnum = threadnum;   
    }  
      
    @Override  
    public synchronized void run() {   
        for(int i = 0;i<100;i++){   
                    System.out.println("NO." + threadnum + ":" + i );  
        }  
        }   
      
        public static void main(String[] args) throws Exception {   
            for(int i=0; i<2; i++){  
                    new TestThread(i).start();  
                    Thread.sleep(1);  
            }  
        }   
}  