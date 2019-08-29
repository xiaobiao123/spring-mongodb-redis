package other.udp;
import java.net.*;
/**
 * 需求：定义一个应用程序，用于接收Udp协议传输的数据并处理
 * 
 * 思路：
 * 1、定义UdpSocket服务，并监听一个端口；
 * 2、定义个数据包，因为要存储接收到的字节数据--数据包对象中封装了提取字节数据信息的方法；
 * 3、通过Socket服务的receive方法将收到的数据存入已定义好的数据包中；
 * 4、通过数据包对象的特有功能，将这些不同的数据去除，打印在控制台上；
 * 5、关闭资源
 */
public class Udpreceive {
    public static void main(String[] args)throws Exception{
        //创建UdpSocket,建立端点
        DatagramSocket ds = new DatagramSocket(11010);
        
        while(true){
            //2、定义数据包用于存储数据
            byte[] buf = new byte[1024];
            /*
             * DatagramPacket(byte[] buf, int length) 
                  构造 DatagramPacket，用来接收长度为 length 的数据包。
             */
            DatagramPacket dp = new DatagramPacket(buf,buf.length);
            
            //3、通过数服务的receive方法，将收到的数据存入数据包中
            ds.receive(dp);
            
            //4、通过数据包的方法获取其中的数据
            String ip = dp.getAddress().getHostAddress();
            System.out.println(ip + "..........is connected");
            String data = new String(dp.getData(),0,dp.getLength());//只打印接收数据的长度，不打印全部缓冲流
            System.out.println(data);
            int port = dp.getPort();
            System.out.println("port:" + port);
            
            //5、关闭资源
            ds.close();
        }
    }
}