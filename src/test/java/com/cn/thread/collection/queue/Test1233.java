package com.cn.thread.collection.queue;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2017/3/24.
 */
public class Test1233 {
    private int queueSize = 10;

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

    public static void main(String[] args) {
        Test1233 test1233 = new Test1233();

        Producer producer = test1233.new Producer();
        Cunsumer cunsumer = test1233.new Cunsumer();
        producer.start();
        cunsumer.start();

    }
    class Cunsumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {

            while (true) {
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {

            while (true) {
                try {
                    queue.put(1);
                    System.out.println("向队列插入一个元素，剩余队列空间：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

