package thread.threadlocal;

public class TestSet {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestSet testSet = new TestSet();


        testSet.set();
        System.out.println(testSet.getLong());
        System.out.println(testSet.getString());


        Thread thread1 = new Thread() {
            public void run() {
                testSet.set();
                System.out.println(testSet.getLong());
                System.out.println(testSet.getString());
            }

            ;
        };
        thread1.start();
        thread1.join();

        System.out.println(testSet.getLong());
        System.out.println(testSet.getString());
    }
}