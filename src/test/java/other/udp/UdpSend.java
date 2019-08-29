package other.udp;

import java.net.*;

/**
 * 需求：通过Udp传输方式，将一段文字数据发送 出去；
 * 
 * 思路：
 * 1、建立UdpSocket服务；
 * 2、提供数据，并将数据封装到数据包中；
 * 3、通过Socket服务的发送功能，将数据包发出去
 * 4、关闭资源
 */
public class UdpSend {
    public static void main(String[] args)throws Exception{
        //1、创建Udp服务，通过DatagramSocket对象
        DatagramSocket ds = new DatagramSocket();
        
        //2、确定数据，并封装成数据包
        byte[] buf = "This's UdpSend test first Demo".getBytes();
        
        /*DatagramPacket(byte[] buf, int length, InetAddress address, int port) 
              构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
         */
         DatagramPacket dp = new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),11010);
         
         //3、通过Socket服务，将已有的数据包发送出去，通过send方法
         ds.send(dp);
         ds.send(dp);

         Thread.sleep(10000);
         //4、关闭资源
         ds.close();
    }
}