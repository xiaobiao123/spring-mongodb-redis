package zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * 监听节点信息的变更（如对节点testUserNode的值进行修改）
 */
public class SubscribeDataChanges {
    public static class ZKDataListener implements IZkDataListener {

        public void handleDataChange(String dataPath, Object data) throws Exception {

            System.out.println("SubscribeDataChanges  handleDataChange============" + dataPath + ":" + data.toString());
        }

        public void handleDataDeleted(String dataPath) throws Exception {

            System.out.println("SubscribeDataChanges  handleDataDeleted============" + dataPath);

        }


    }

    public static void main(String[] args) throws InterruptedException {
        //zk集群的地址  
        String ZKServers = "172.30.22.239";

//        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());

        ZkClient zkClient = new ZkClient(ZKServers, 10000, 10000, new ZkSerializer() {
            public byte[] serialize(Object data) throws ZkMarshallingError {
                return data == null ? new byte[0] : data.toString().getBytes();
            }

            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                return bytes != null && bytes.length != 0 ? new String(bytes) : null;
            }
        });

        System.out.println("conneted ok!");

        zkClient.subscribeDataChanges("/testUserNode", new ZKDataListener());
        Thread.sleep(Integer.MAX_VALUE);

    }
}  