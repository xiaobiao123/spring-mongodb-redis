package other.thread00001;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger value = new AtomicInteger();

    public int getValue() {
        return value.get();
    }

    public int increment() {
        return value.incrementAndGet();
    }

    public int increment(int i) {
        return value.addAndGet(i);
    }

    public int decrement() {
        return value.decrementAndGet();
    }

    public int decrement(int i) {
        return value.addAndGet(-i);
    }
}