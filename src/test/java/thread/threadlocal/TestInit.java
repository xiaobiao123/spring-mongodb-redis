package thread.threadlocal;

public class TestInit {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        protected Long initialValue() {
            return Thread.currentThread().getId();
        };
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
        protected String initialValue() {
            return Thread.currentThread().getName();
        };
    };


//    public void set() {
//        longLocal.set(Thread.currentThread().getId());
//        stringLocal.set(Thread.currentThread().getName());
//    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestInit testInit = new TestInit();

//        testInit.set();
        System.out.println(testInit.getLong());
        System.out.println(testInit.getString());


        Thread thread1 = new Thread(){
            public void run() {
//                testInit.set();
                System.out.println(testInit.getLong());
                System.out.println(testInit.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(testInit.getLong());
        System.out.println(testInit.getString());
    }
}