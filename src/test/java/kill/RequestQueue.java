package kill;

import org.springframework.http.HttpRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestQueue {
    public static ConcurrentLinkedQueue<HttpRequest> queue = new ConcurrentLinkedQueue();
} 