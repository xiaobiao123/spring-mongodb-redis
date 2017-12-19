package cn.springmvc.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
    public static void main(String[] args){  
        ArrayList<String> arrL = new ArrayList<String>();
        ArrayList<String> arrLTmp1 = new ArrayList<String>();  
        ArrayList<String> arrLTmp2 = new ArrayList<String>();  
        ArrayList<String> arrLTmp3 = new ArrayList<String>();  
        ArrayList<String> arrLTmp4 = new ArrayList<String>();  
        for (int i=0;i<100000;i++){
            arrL.add("第"+i+"个");  
        }  
        long t1 = System.nanoTime();  
        //方法1  
        Iterator<String> it = arrL.iterator();
        while(it.hasNext()){  
            arrLTmp1.add(it.next());  
        }  
        long t2 = System.nanoTime();  
        //方法2  
        for(Iterator<String> it2 = arrL.iterator();it2.hasNext();){  
            arrLTmp2.add(it2.next());  
        }  
        long t3 = System.nanoTime();  
        //方法3  
        for (String vv :arrL){  
            arrLTmp3.add(vv);  
        }  
        long t4 = System.nanoTime();  
        //方法4
        int k=arrL.size();
        for(int i=0;i<k;i++){
            arrLTmp4.add(arrL.get(i));  
        }  
        long t5 = System.nanoTime();  
        System.out.println("第一种方法耗时：" + (t2-t1)/1000 + "微秒");  
        System.out.println("第二种方法耗时：" + (t3-t2)/1000 + "微秒");  
        System.out.println("第三种方法耗时：" + (t4-t3)/1000 + "微秒");  
        System.out.println("第四种方法耗时：" + (t5-t4)/1000 + "微秒");  
          
          
    }  
  
}  