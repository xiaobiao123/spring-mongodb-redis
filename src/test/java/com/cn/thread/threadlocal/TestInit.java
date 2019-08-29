package com.cn.thread.threadlocal;

import com.google.common.collect.Lists;

import java.util.List;

public class TestInit {

    ThreadLocal<List<Long>> list = new ThreadLocal<List<Long>>() {
        protected List<Long> initialValue() {
            List<Long> longList = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                longList.add(Long.valueOf(i));
            }
            return longList;
        }

        ;
    };

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }

        ;
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        ;

        protected String initialValue() {
            return Thread.currentThread().getName();
        }

        ;
    };

    public Long getList() {
        Long ss = list.get().get(0);
        list.get().remove(0);
        return ss;

    }

    public void setList(Long list) {
        this.list.get().add(list);
    }

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


        Thread thread1 = new Thread() {
            public void run() {
//                testInit.set();
                System.out.println(testInit.getLong());
                System.out.println(testInit.getString());
            }

            ;
        };
        thread1.start();
        thread1.join();

        System.out.println(testInit.getLong());
        System.out.println(testInit.getString());
    }
}