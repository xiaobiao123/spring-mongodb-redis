package com.cn.thread.thread;

/**
 * 但是一般情况下不建议通过这种方式来中断线程，一般会在MyThread类中增加一个属性
 * isStop来标志是否结束while循环，然后再在while循环中判断isStop的值。
 */
class MyThread extends Thread{
        private volatile boolean isStop = false;
        @Override
        public void run() {
            int i = 0;
            while(!isStop){
                System.out.println("isStop:"+isStop);

                if (i==3){
                    this.setStop(true);
                    System.out.println("isStop===true:"+isStop);
                }
                i++;
                System.out.println(i);
            }
        }
         
        public void setStop(boolean stop){
            this.isStop = stop;
        }
    }


class TestTread{
    public static void main(String[] args) {

        for (int i = 0; i <10 ; i++) {
            MyThread thread=new MyThread();
            thread.start();
        }


    }

}
