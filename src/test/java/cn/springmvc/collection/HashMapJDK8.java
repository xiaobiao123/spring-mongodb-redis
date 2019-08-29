//package cn.springmvc.collection;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * @author gwb
// * @date 2019/8/23 15:54
// */
//
//public class HashMapJDK8<K,V> {
//
//
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//
//
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//
//    static final int TREEIFY_THRESHOLD = 8;
//
//
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//
//    transient HashMapJDK8.Node<K,V>[] table;
//
//    /**
//     * Holds cached entrySet(). Note that AbstractMap fields are used
//     * for keySet() and values().
//     */
//    transient Set<Map.Entry<K,V>> entrySet;
//
//
//    transient int size;
//
//
//    transient int modCount;
//
//    int threshold;
//
//
//    final float loadFactor;
//
//
//
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V                 value;
//        HashMapJDK8.Node<K,V> next;
//
//        Node(int hash, K key, V value, HashMapJDK8.Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        // 默认容量初始化
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        //如果table[i]为空，那就把这个键值对放在table[i], i = (n - 1) & hash 相等于 hash % n,
//        //但是hash后按位与 n-1，比%模运算取余要快
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//            //当另一个key的hash值已经存在时
//        else {
//            Node<K,V> e; K k;
//            // table[i].key == key
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//                //JDK8在哈希碰撞的链表长度达到TREEIFY_THRESHOLD（默认8)后，
//                //会把该链表转变成树结构，提高了性能。
//            else if (p instanceof TreeNode)
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {
//                //遍历table[i]所对应的链表，直到最后一个节点的next为null或者有重复的key值
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            //key重复，替换value
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    // Callbacks to allow LinkedHashMap post-actions
//    void afterNodeAccess(Node<K,V> p) { }
//    void afterNodeInsertion(boolean evict) { }
//    void afterNodeRemoval(Node<K,V> p) { }
//
//
//
//    final Node<K,V>[] resize() {
//        Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            // 超过最大值就不再扩充了，就只好随你碰撞去吧
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            // 没超过最大值，就扩充为原来的2倍
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                    oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold
//        }
//        else if (oldThr > 0) //初始容量设置为阈值
//            newCap = oldThr;
//        else {               // 初始阈值为零表示使用默认值
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        // 计算新的resize上限
//        if (newThr == 0) {
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
//                    (int)ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes","unchecked"})
//        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            // 把每个bucket都移动到新的buckets中
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K,V> e;
//                if ((e = oldTab[j]) != null) {
//                    // 清除原来table[i]中的值
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)
//                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//                    else { // 带有链表时优化重hash
//                        Node<K,V> loHead = null, loTail = null;
//                        Node<K,V> hiHead = null, hiTail = null;
//                        Node<K,V> next;
//                        do {
//                            next = e.next;
//                            // 原索引，(e.hash & oldCap) == 0 说明 在put操作通过 hash & newThr
//                            //计算出的索引值等于现在的索引值。
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            }
//                            // 原索引+oldCap，不是原索引，就移动原来的长度
//                            else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        // 原索引放到bucket里
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        // 原索引+oldCap放到bucket里
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//}
//
