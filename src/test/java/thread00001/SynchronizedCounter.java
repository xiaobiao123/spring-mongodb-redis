package thread00001;

public class SynchronizedCounter {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return ++value;

    }

    public synchronized int decrement() {
        return --value;
    }

    public static void main(String[] args) {


        new Thread(){
            SynchronizedCounter counter=new SynchronizedCounter();
            public void run() {
                System.out.println("counter："+counter.increment());

            };

        }.start();


        System.out.println("==================");

        new Thread(){
            SynchronizedCounter counter2=new SynchronizedCounter();
            public void run() {
                System.out.println("counter2："+counter2.increment());
            };
        }.start();




    }

}