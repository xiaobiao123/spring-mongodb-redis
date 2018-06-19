package cn.springmvc.collection.String类为什么是final的;

import com.google.common.base.Function;
import thread.collection.hasmset.HashMapSource;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class IntegerTest {
    public static void main(String[] args) {
        objPoolTest();
    }

    public static void objPoolTest() {
        //Integer i1 = 40;
        //Integer i2 = 40;
        //Integer i3 = 0;
        //
        ////通过new创建的对象不会放入常量池，会放入堆内存上
        //Integer i4 = new Integer(40);
        //Integer i5 = new Integer(40);
        //Integer i6 = new Integer(0);
        //
        //System.out.println("i1=i2\t" + (i1 == i2)); //true
        //System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));//true
        //System.out.println("i4=i5\t" + (i4 == i5));      // false
        //System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));  // TODO true
        //
        ////i1==i2+i3、i4==i5+i6结果为True，是因为，Java的数学计算是在内存栈里操作的，
        ////Java会对i5、i6进行拆箱操作，其实比较的是基本类型（40=40+0），他们的值相同，因此结果为True。
        //
        //String str1 = "str";
        //String str2 = "ing";
        //
        ////会放入常量池
        //String str3 = "str" + "ing";
        //
        ////不会放入常量池
        //String str4 = str1 + str2;
        //System.out.println(str3 == str4);//false
        //
        //
        //// Double 和Float 没有实现常量池
        //Double d1 = 1.2;
        //Double d2 = 1.2;
        //System.out.println(d1 == d2);//输出false
        ////System.out.println();
        //
        //HashMapSource<String, String> map = new HashMapSource<>();
        //


        //TreeMap<String, String> treeMap = new TreeMap();
        //
        //treeMap.put("10", "value");
        //treeMap.put("301", "value");
        //treeMap.put("40", "value");
        //treeMap.put("401", "value");
        //treeMap.put("50", "value");
        //treeMap.put("501", "value");
        //treeMap.put("101", "value");
        //treeMap.put("20", "value");
        //treeMap.put("201", "value");
        //treeMap.put("30", "value");
        //treeMap.put("60", "value");
        ////treeMap.put("601", "value");
        ////treeMap.put("102", "value");
        ////treeMap.remove("30");
        //
        //treeMap.forEach((k,v)->{
        //    System.out.println("Item : " + k + " Count : " + v);
        //    if("E".equals(k)){
        //        System.out.println("Hello E");
        //    }
        //});
        //
        //
        //List<String> items = new ArrayList<>();
        //items.add("A");
        //items.add("B");
        //items.add("C");
        //items.add("D");
        //items.add("E");
        ////输出：A,B,C,D,E
        //items.forEach(item->System.out.println(item));
        ////输出 : C
        //items.forEach(item->{
        //    if("C".equals(item)){
        //        System.out.println(item);
        //    }
        //});
        //
        //    String ss="xxxxxxxxx";
        //Function<String, Boolean> stringBooleanFunction = ss::startsWith;


        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("xxxxxxxx","ssssssss");



    }
} 