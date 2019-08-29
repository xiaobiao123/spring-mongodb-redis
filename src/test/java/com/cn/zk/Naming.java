package com.cn.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class Naming {
    private ZooKeeper zk = null; // ZooKeeper对象  
    private String nameroot = "/NameService";
    private String namerootvalue = "IsNameService";
    private String namevalue = "IsName";

    /**
     * @函数：命名服务构造函数
     * @参数：zk的地址端口 描述：初始化zk实例，创建命名服务根路径
     */
    public Naming(String url) {
        try {
            // 初始化，如果当前有alive的zk连接则先关闭  
            if (zk != null && zk.getState().isAlive() == true)
                zk.close();
            zk = new ZooKeeper(url, 30000, null); // 重新建立连接  
            System.out.println("zookeeper connect success:url=" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 判断是否有/NameService，如果没有，则创建该路径，用来作为所有的集中配置信息的根目录  
        try {
            if (zk.exists(nameroot, false) == null) {
                zk.create(nameroot, namerootvalue.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println(nameroot + " create success!");
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * @函数： 注销zk实例
     */
    public void UnNaming() {
        if (zk != null) {
            try {
                zk.close();
                System.out.println("zookeeper close success!");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            zk = null;
        }
    }

    /**
     * @函数：注册一个全局名字
     * @描述：待注册的名字字符串name，在zk中创建一个/NameService/name的znode路径
     * @参数： 待注册的名字字符串name
     * @返回值： 0 表示注册成功 -1 表示出错 1 表示该命名已被注册
     */
    @SuppressWarnings("finally")
    public int Registered(String name) {
        String path = nameroot + "/" + name;
        int ret = 0;
        try {
            if (zk.exists(path, false) == null) {
                zk.create(path, namevalue.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println(name + " registered success!");
            } else {
                ret = 1;
                System.out.println(name + " is exists, can not regist again!");
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block  
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            return ret;
        }
    }

    /**
     * @函数：注销一个全局名字
     * @描述：待注销的名字字符串name，在zk中删除/NameService/name的znode路径
     * @参数： 待注销的名字字符串name
     * @返回值： 0 表示注销成功 -1 表示出错 1 表示该命名未注册，不存在命名服务系统中
     */
    @SuppressWarnings("finally")
    public int Canceled(String name) {
        String path = nameroot + "/" + name;
        int ret = 0;
        try {
            if (zk.exists(path, false) != null) {
                zk.delete(path, -1);
                System.out.println(name + " canceled success!");
            } else {
                ret = 1;
                System.out.println(name + " is not exists, can not canceled!");
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block  
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            return ret;
        }
    }

    /**
     * @函数：获取命名服务系统的所有命名
     * @描述：
     * @参数：
     * @返回值：命名列表
     */
    public List<String> Readall() {
        List<String> namelist = new ArrayList<String>();
        try {
            namelist = zk.getChildren(nameroot, false);
        } catch (KeeperException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
        return namelist;
    }

}  