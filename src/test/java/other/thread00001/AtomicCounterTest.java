package other.thread00001;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounterTest extends Thread {
    AtomicCounter counter;

    AtomicLong balance;

    public AtomicCounterTest(AtomicCounter counter) {
        this.counter = counter;

    }

    @Override
    public void run() {
        int i = counter.increment();
        System.out.println("generated  out number:" + i);
    }

    public static void main(String[] args) {

        AtomicCounter counter = new AtomicCounter();
        for (int i = 0; i < 10; i++) {//10个线程
            new AtomicCounterTest(counter).start();
        }
    }
}