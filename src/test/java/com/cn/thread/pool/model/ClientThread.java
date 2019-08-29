//package com.cn.thread.pool.model;
//
//import java.util.Random;
////向Channel发送Request请求的
//public class ClientThread extends Thread{
//    private final Channel channel;
//    private static final Random random=new Random();
//
//    public ClientThread(String name,Channel channel)
//    {
//        super(name);
//        this.channel=channel;
//    }
//    public void run()
//    {
//        try{
//            for(int i=0;true;i++)
//            {
//                Runnable request=new Request(getName(),i);
//                channel.putRequest(request);
//                Thread.sleep(random.nextInt(1000));
//            }
//        }catch(InterruptedException e)
//        {
//        }
//    }
//}