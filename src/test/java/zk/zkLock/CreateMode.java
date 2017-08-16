//package zk.zkLock;
//
//import org.apache.zookeeper.KeeperException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public enum CreateMode {
//
//    /**
//     * 持久节点：节点创建后，会一直存在，不会因客户端会话失效而删除；
//     */
//    PERSISTENT (0, false, false),
//
//    /**
//    * 持久顺序节点：基本特性与持久节点一致，创建节点的过程中，zookeeper会在其名字后自动追加一个单调增长的数字后缀，作为新的节点名；
//    */
//    PERSISTENT_SEQUENTIAL (2, false, true),
//
//    /**
//     *  临时节点：客户端会话失效或连接关闭后，该节点会被自动删除，且不能再临时节点下面创建子节点，否则报如下错：org.apache.zookeeper.KeeperException$NoChildrenForEphemeralsException；
//     */
//    EPHEMERAL (1, true, false),
//
//    /**
//     * 临时顺序节点：基本特性与临时节点一致，创建节点的过程中，zookeeper会在其名字后自动追加一个单调增长的数字后缀，作为新的节点名；
//     */
//    EPHEMERAL_SEQUENTIAL (3, true, true);
//    private static final Logger LOG = LoggerFactory.getLogger(CreateMode.class);
//    private boolean ephemeral;
//    private boolean sequential;
//    private int flag;
//    CreateMode(int flag, boolean ephemeral, boolean sequential) {
//        this.flag = flag;
//        this.ephemeral = ephemeral;
//        this.sequential = sequential;
//    }
//    public boolean isEphemeral() {
//        return ephemeral;
//    }
//    public boolean isSequential() {
//        return sequential;
//    }
//    public int toFlag() {
//        return flag;
//    }
//    static public CreateMode fromFlag(int flag) throws KeeperException {
//        switch(flag) {
//        case 0: return CreateMode.PERSISTENT;
//        case 1: return CreateMode.EPHEMERAL;
//        case 2: return CreateMode.PERSISTENT_SEQUENTIAL;
//        case 3: return CreateMode.EPHEMERAL_SEQUENTIAL ;
//        default:
//            LOG.error("Received an invalid flag value to convert to a CreateMode");
//            throw new KeeperException.BadArgumentsException();
//        }
//    }
//}