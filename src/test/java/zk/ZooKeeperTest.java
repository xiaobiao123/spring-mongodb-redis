package zk;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;

import static org.jboss.netty.handler.codec.rtsp.RtspHeaders.Values.CLIENT_PORT;

public class ZooKeeperTest {

    private static final int TIME_OUT = 3000;
    private static final String HOST = "172.30.22.11:2181";

    private static ZooKeeper zookeeper;

    //static {
    //    try {
    //        zookeeper = new ZooKeeper(HOST, TIME_OUT, null);
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    static {
        // 创建一个与服务器的连接
        try {
            //            new ZkClient(zkUrl);
            zookeeper = new ZooKeeper(HOST,
                    3000, new Watcher() {
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                    System.out.println("路径为" + event.getPath());

                    System.out.println("已经触发了" + event.getType() + "事件！");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建节点
     *
     * @throws Exception
     */
    @Test
    public void createNode() throws Exception {

        if (zookeeper.exists("/test", true) == null) {
            System.out.println("节点名称为test不存在");
            zookeeper.create("/test", "Znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println("节点名称为test");
        }
    }

    /**
     * 创建节点
     *
     * @throws Exception
     */
    @Test
    public void createChildNode() throws Exception {
        if (zookeeper.exists("/test/child", false) == null) {
            System.out.println("节点名称为test不存在");
            zookeeper.create("/test/child", "child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println("节点名称为test已存在");
        }
    }

    @Test
    public void getChildNode() throws Exception {
        System.out.println(zookeeper.getChildren("/test", false));
    }

    /**
     * 修改节点
     *
     * @throws Exception
     */
    @Test
    public void setNodeValue() throws Exception {
        String data = "zNode2";
        zookeeper.setData("/test", data.getBytes(), -1);
    }

    /**
     * 获取节点
     *
     * @throws Exception
     */
    @Test
    public void getNodeValue() throws Exception {
        String s = new String(zookeeper.getData("/test/child", false, null), "UTF-8");
        System.out.println("xxxxxxxxxxxxxx:" + s);
        zookeeper.close();
    }

    /**
     * 删除节点
     *
     * @throws Exception
     */
    @Test
    public void deleteNode() throws Exception {
        String data = "zNode2";
        zookeeper.delete(data, -1);
        zookeeper.close();
    }

    public void createWatcher() throws IOException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT,
                3000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });
    }


    public static void main(String[] args) throws Exception {

        Thread.sleep(100000);
        //        System.out.println("=========创建节点===========");
        //        if(zookeeper.exists("/test", false) == null)
        //        {
        //            zookeeper.create("/test", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
        // .PERSISTENT);
        //        }
        //        System.out.println("=============查看节点是否安装成功===============");
        //        System.out.println(new String(zookeeper.getData("/test", false, null)));
        //
        //        System.out.println("=========修改节点的数据==========");
        //        String data = "zNode2";
        //        zookeeper.setData("/test", data.getBytes(), -1);
        //
        //        System.out.println("========查看修改的节点是否成功=========");
        //        System.out.println(new String(zookeeper.getData("/test", false, null)));
        //
        //        System.out.println("=======删除节点==========");
        //        zookeeper.delete("/test", -1);
        //
        //        System.out.println("==========查看节点是否被删除============");
        //        System.out.println("节点状态：" + zookeeper.exists("/test", false));
        //
        //        zookeeper.close();
    }
}