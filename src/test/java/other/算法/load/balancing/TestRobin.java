package other.算法.load.balancing;

import org.junit.Test;

/**
 * Created by gwb on 2018/1/15.
 * <p>
 * https://www.cnblogs.com/szlbm/p/5588555.html
 */
public class TestRobin {
    @Test
    public void testRobin() {
        for (int i = 0; i < 10; i++) {
            System.out.println(RoundRobin.getServer());
        }

    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Random.getServer());
        }

    }

    @Test
    public void testHash() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Hash.getServer());
        }

    }

    @Test
    public void testWeightRoundRobin() {
        for (int i = 0; i < 10; i++) {
            System.out.println(WeightRoundRobin.getServer());
        }

    }


    @Test
    public void testWeightRandom() {
        for (int i = 0; i < 10; i++) {
            System.out.println(WeightRandom.getServer());
        }

    }

    @Test
    public void testMathMax() {
        System.out.println(Math.max(11, 10));

    }


}
