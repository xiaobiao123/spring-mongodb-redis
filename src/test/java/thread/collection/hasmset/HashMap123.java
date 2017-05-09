//package thread.collection.hasmset;
//
//import java.util.HashMap;
//
///**
// * Created by Administrator on 2017/3/30.
// */
//public class HashMap123<K, V> {
//
//    transient HashMapSource.Entry[] table;
//
//    transient HashMap.Entry[] map123;
//
//
//    public void get() {
//        // 遍历table[0]里的所有桶
//        for (HashMapSource.Entry<K, V> e = table[0]; e != null; e = e.next) {
//            // 看看桶的key是不是null，是则返回相应值
//            if (e.key == null) {
//                System.out.println(e.value);
//            }
//
//        }
//
//    }
//
//
//}
