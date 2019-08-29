package cn.springmvc.collection;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class TestList {
    public static void main(String[] args) {

        //		List<String> list = new ArrayList<String>();
        //		list.add("1");
        //		list.add("2");
        //		list.add("3");
        //
        //		list.get(2);
        //
        //		List<Map<String,String>> listss=new ArrayList<>();
        //
        //		LinkedList<String> linkedList=new LinkedList<>();
        //		linkedList.add("SSS");


        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("shopNo", "shopNo");
        map.put("shopName", "shopName");
        list.add(map);

        map.size();


        Map<String, String> map1 = new HashMap<>();
        String              put  = map1.put("shopNo", "shopNo1");

        String put1 = map1.put("shopNo", "shopName1");

        list.add(map1);
        System.out.println("testQueryCustomers  success..." + JSON.toJSONString(list));

        Iterator<Map<String, String>> iterator = list.iterator();

        System.out.println(1 << 5);



    }

    @Test
    public void testHashTable() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(10);

        Hashtable hashtable = new Hashtable();
        hashtable.put("ddd", "xxx");

        Integer i=12345678;
        Integer k=12345678;
        Set<String> set=new HashSet<>();
        set.add(null);
        System.out.println(i==k);

    }

    @Test
    public void testRetrurnFinally() {
        int i = 1;
        try {
            System.out.println("try里面的i : " + i);
        } finally {
            System.out.println("进入finally...");
            ++i;
            System.out.println("fianlly里面的i : " + i);
        }


        String k = "kkkkk";
        try {
            System.out.println("try里面的k : " + k);
            //return k;
        } finally {
            System.out.println("进入finally...");
            k = k + "xxxxxx";
            System.out.println("fianlly里面的i : " + k);
        }


    }
}