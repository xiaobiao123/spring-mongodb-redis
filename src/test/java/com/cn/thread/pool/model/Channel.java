//package com.cn.thread.pool.model;
//
////用到了生产者与消费者模式
////生成线程池，接受客户端线程的请求，找到一个工作线程分配该客户端请求
//public class Channel {
//    private static final int MAX_REQUEST = 100;// 并发数目，就是同时可以接受多少个客户端请求
//    //利用数组来存放请求，每次从数组末尾添加请求，从开头移除请求来处理
//    private final Request[] requestQueue;// 存储接受客户线程的数目
//    private int tail;//下一次存放Request的位置
//    private int head;//下一次获取Request的位置
//    private int count;// 当前request数量
//    private final WorkerThread[] threadPool;// 存储线程池中的工作线程
//    // 运用数组来存储
//    public Channel(int threads) {
//        this.requestQueue = new Request[MAX_REQUEST];
//        this.head = 0;
//        this.head = 0;
//        this.count = 0;
//        threadPool = new WorkerThread[threads];
//        // 启动工作线程
//        for (int i = 0; i < threadPool.length; i++) {
//            threadPool[i] = new WorkerThread("Worker-" + i, this);
//        }
//    }
//    public void startWorkers() {
//        for (int i = 0; i < threadPool.length; i++) {
//            threadPool[i].start();
//        }
//    }
//    // 接受客户端请求线程
//    public synchronized void putRequest(Request request) {
//        // 当Request的数量大于或等于同时接受的数目时候，要等待
//        while (count >= requestQueue.length)
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
//        requestQueue[tail] = request;
//        tail = (tail + 1) % requestQueue.length;
//        count++;
//        notifyAll();
//    }
//    // 处理客户端请求线程
//    public synchronized Request takeRequest() {
//        while (count <= 0)
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
//        Request request = requestQueue[head];
//        head = (head + 1) % requestQueue.length;
//        count--;
//        notifyAll();
//        return request;
//    }
//}