package socket;

import cn.springmvc.model.User;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class ClientSocket {
 public static void main(String args[]) {
    String host = "127.0.0.1";
    int port = 8919;
    try {
     Socket client = new Socket(host, port);
     Writer writer = new OutputStreamWriter(client.getOutputStream());
     User user=new User();
     user.setNickname("12313");
     user.setPassword("password");


     writer.write(JSON.toJSONString(user));
     writer.flush();
     //writer.close();
     //client.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
  }
  
}