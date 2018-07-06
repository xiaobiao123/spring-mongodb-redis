package zk.zkLock.ww;

/**
 * Created by gwb on 2018-06-26.
 */
public class Test {


    @org.junit.Test
    public void getLock(){
        DistributedLock lock   = new DistributedLock("192.168.1.127:2181","lock");
        lock.lock();
        //共享资源
        if(lock != null)
            lock.unlock();
    }
}
