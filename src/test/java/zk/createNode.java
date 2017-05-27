package zk;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;


public class createNode {

    private static ZkClient zkClient;

    static {
        String ZKServers = "172.30.22.239";
        zkClient = new ZkClient(ZKServers, 10000, 10000, new SerializableSerializer());
//        zkClient = new ZkClient(ZKServers, 10000, 10000,  new ZkSerializer() {
//            public byte[] serialize(Object data) throws ZkMarshallingError {
//                return data == null ? new byte[0] : data.toString().getBytes();
//            }
//
//            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
//                return bytes != null && bytes.length != 0 ? new String(bytes) : null;
//            }
//        });
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() {
        //zk集群的地址  
//        String ZKServers = "192.168.30.164:2181,192.168.30.165:2181,192.168.30.166:2181";
        User user = new User();
        user.setId(1);
        user.setName("testUser");

        /**
         * "/testUserNode" :节点的地址 
         * user：数据的对象 
         * CreateMode.PERSISTENT：创建的节点类型 
         */
        String path = zkClient.create("/testUserNode", user, CreateMode.PERSISTENT);
        //输出创建节点的路径
        System.out.println("created path:" + path);
    }

    /**
     * 更新数据
     */
    @Test
    public void updateData() {
        User user = new User();
        user.setId(2);
        user.setName("testUser2424");
        /**
         * testUserNode 节点的路径
         * user 传入的数据对象
         */
        zkClient.writeData("/testUserNode", user);
    }


    /**
     * 获取节点数据
     */
    @Test
    public void getData() {
        Stat stat = new Stat();
        //获取 节点中的对象
        User user = zkClient.readData("/testUserNode", stat);
        System.out.println(user.getName());
        System.out.println(stat);
    }

    /**
     * 获取子节点
     */
    @Test
    public void getChilden() {
        //获取 节点中的对象
        List<String> user = zkClient.getChildren("/testUserNode");
        for (String node:user){
            zkClient.subscribeDataChanges("/testUserNode", new SubscribeDataChanges.ZKDataListener());
        }

        System.out.println(JSON.toJSONString(user));
    }


    /**
     * 判断节点是否存在
     */
    @Test
    public void exists() {
        boolean e = zkClient.exists("/testUserNode");
        //返回 true表示节点存在 ，false表示不存在
        System.out.println(e);
    }


    /**
     * 删除节点
     */
    @Test
    public void deleteNode() {
        //删除单独一个节点，返回true表示成功
        boolean e1 = zkClient.delete("/testUserNode");
//        List<String> list=zkClient.getChildren("/testUserNode");
//        for (String node:list){
//            System.out.println(node);
//        }

        //删除含有子节点的节点
        boolean e2 = zkClient.deleteRecursive("/testUserNode");

        //返回 true表示节点成功 ，false表示删除失败
        System.out.println(e1);


    }


}