package thread.collection.currentHashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/3/21.
 * 1
 */
public class TestHashMapException {

//    public static void main(String[] args) {
////        HashMapException();
//        HashMapSuccess();
//    }

    //HashMap或者ArrayList边遍历边删除数据会报java.util.ConcurrentModificationException异常
    @Test
    public  void HashMapException() {
        Map<Long, String> mReqPacket = new HashMap<Long, String>();
        for (long i = 0; i < 15; i++) {
            mReqPacket.put(i, i + "");
        }

        for (Map.Entry<Long, String> entry : mReqPacket.entrySet()) {
            long key = entry.getKey();
            String value = entry.getValue();
            if (key < 10) {
                mReqPacket.remove(key);
            }
        }

        for (Map.Entry<Long, String> entry : mReqPacket.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    //所以要用迭代器删除元素：
    @Test
    public  void HashMapSuccess() {
        Map<Long, String> mReqPacket = new HashMap<Long, String>();
        for (long i = 0; i < 15; i++) {
            mReqPacket.put(i, i + "");
        }

        for (Iterator<Map.Entry<Long, String>> iterator = mReqPacket.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Long, String> entry = iterator.next();
            long key = entry.getKey();
            if (key < 10) {
                iterator.remove();
            }
        }

        for (Map.Entry<Long, String> entry : mReqPacket.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    /**
     * 对ConcurrentHashMap边遍历边删除或者增加操作不会产生异常(可以不用迭代方式删除元素)，
     * 因为其内部已经做了维护，遍历的时候都能获得最新的值。即便是多个线程一起删除、添加元素也没问题。
     */
    @Test
    public  void ConcurrentHashMapTest() {
        Map<Long, String> conMap = new ConcurrentHashMap<Long, String>();
        for (long i = 0; i < 15; i++) {
            conMap.put(i, i + "");
        }

        for (Map.Entry<Long, String> entry : conMap.entrySet()) {
            long key = entry.getKey();
            if (key < 10) {
                conMap.remove(key);
            }
        }

        for (Map.Entry<Long, String> entry : conMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 一个线程对ConcurrentHashMap增加数据，另外一个线程在遍历时就能获得。
     */
    @Test
    public  void ConcurrentHashMapTest2() {
        Map<Long, String> conMap = new ConcurrentHashMap<Long, String>();
        for (long i = 0; i < 15; i++) {
            conMap.put(i, i + "");
        }

        for (Map.Entry<Long, String> entry : conMap.entrySet()) {
            long key = entry.getKey();
            if (key < 10) {
                conMap.remove(key);
            }
        }

        for (Map.Entry<Long, String> entry : conMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
