package thread.thread;

/**
 * Created by Administrator on 2017/3/21.
 */
public class TestMyThread {
    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.setStop(false);
        myThread.start();
    }
}

