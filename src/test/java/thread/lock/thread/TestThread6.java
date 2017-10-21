package thread.lock.thread;

public class TestThread6 {
    public static void main(String[] args) {

        int num = 0;

        System.out.println("increasexxxxxxxxxxxxxxxxxxxxxxxx:" + ++num);

        final NumberExecute ne = new NumberExecute();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (true) {
                    ne.increase();
                    i++;
                    if (i >= 100) {
                        break;
                    }
                }
            }
        });

        thread.start();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (true) {
                    ne.decrease();
                    i++;
                    if (i >= 100) {
                        break;
                    }
                }
            }
        });
        thread1.start();

        System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
    }

}

class NumberExecute {

    private int num;

    public synchronized void increase() {
        while (num != 0) {
            try {
                //如果num不为0，当前线程等待。
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("increase:" + num++);
        //唤醒在等待线程
        notify();
    }

    public synchronized void decrease() {
        while (num == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("decrease :" + num--);
        notify();
    }
}