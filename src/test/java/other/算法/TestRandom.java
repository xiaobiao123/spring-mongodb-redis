package other.算法;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

/**
 * Created by gwb on 2017/10/21.
 */
public class TestRandom {
    @Test
    public void testRandom2() {

        Random random = new Random();
        int i = random.nextInt(2);
        System.out.println("完了，集合大小为" + i);
    }


    @Test
    public void testRandom() {
        int value = 10000000;
        //int类型最大值：2的32次方 - 1 = Integer.MAX_VALUE = 2147483647，二十亿多,真够啦 。
        Set<Integer> result = Sets.newHashSetWithExpectedSize(value);
        Random random = new Random();
        long a = System.currentTimeMillis();
        while (result.size() < value + 1) {
            int i = random.nextInt(value + 1);
            result.add(i);
        }
        System.out.println("\r<br> 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + result.size());
    }


    @Test
    public void createRandom() {

        Random random = new Random();
        long start = System.currentTimeMillis();

        int value = 10000000;
        ArrayList<Integer> list = new ArrayList<Integer>(value);
        for (int j = 1; j <= value; ++j) {
            list.add(j);
        }

        int index = 0;
        int count = 0;
        int tmp = 0;
        while (value > 0) {
            index = random.nextInt(value);
            // System.out.println(list.get(index));
            tmp = list.get(count + index);
            list.set(count + index, list.get(count));
            list.set(count, tmp);
            ++count;
            --value;
        }

        long end = System.currentTimeMillis();

        //----验证是否正确
        Collections.sort(list);
        int i = 0, size = list.size();
        for (; i < size; ++i) {
            if (list.get(i) != (i + 1))
                System.out.println(i + "error" + list.get(i));
        }
        //----验证是否正确

        System.out.println("creat3------");
        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
        System.out.println("集合大小为" + list.size());
    }

    @Test
    public void createRandom4() {

        Random random = new Random();
        long start = System.currentTimeMillis();

        int value = 10000000;

        int[] list = new int[value];

        for (int j = 0; j < value; ++j) {
            list[j] = j + 1;
        }

        int index = 0;
        int count = 0;
        int tmp = 0;
        while (value > 0) {
            index = random.nextInt(value);
            //             System.out.println(list[count + index]);
            tmp = list[count + index];
            list[count + index] = list[count];
            list[count] = tmp;
            ++count;
            --value;
        }

        long end = System.currentTimeMillis();

        //----验证是否正确
        //        Collections.sort(list);
        //        int i = 0, size = list.length;
        //        for (; i < size; ++i) {
        //            if (list[i] != (i + 1))
        //                System.out.println(i + "error" + list[i]);
        //        }
        //----验证是否正确

        System.out.println("creat4------");
        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + list.length);
    }

}
