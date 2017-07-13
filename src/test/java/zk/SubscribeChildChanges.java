package zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.List;

/**
 * 子节点的信息变动(创建/删除)
 */
public class SubscribeChildChanges {

    private static  ZkClient zkClient;
    private  static final String ZKServers = "172.30.21.92";

    static {
         zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());
    }


    private static class ZKChildListener implements IZkChildListener {
        /** 
         * handleChildChange： 用来处理服务器端发送过来的通知 
         * parentPath：对应的父节点的路径 
         * currentChilds：子节点的相对路径 
         */  
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {

            zkClient.getChildren("/testUserNode");

            System.out.println("SubscribeChildChanges  handleChildChange=========="+parentPath);
            System.out.println("SubscribeChildChanges  handleChildChange=========="+currentChilds.toString());
              
        }  
          
    }  
      
    public static void main(String[] args) throws InterruptedException {  
        //zk集群的地址  
//        String ZKServers = "172.30.22.239";
//        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());
        System.out.println("conneted ok!");  
        /** 
         * "/testUserNode" 监听的节点，可以是现在存在的也可以是不存在的 
         */  
        zkClient.subscribeChildChanges("/testUserNode", new ZKChildListener());
        Thread.sleep(Integer.MAX_VALUE);  
    }  
}  