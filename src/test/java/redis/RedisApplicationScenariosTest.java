package redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * redis应用场景
 * 参考地址
 * url:https://my.oschina.net/lnmpstudy/blog/266541
 *
 *
 * https://my.oschina.net/ydsakyclguozi/blog/421070?p={{totalPage}}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext-redis.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisApplicationScenariosTest {

    private static Logger logger = Logger.getLogger(RedisApplicationScenariosTest.class);

    /*不支持多命令操作*/
    @Autowired
    private ShardedJedisPool redisPool;

    /*支持多命令操作*/

    public static String dbName = "test";
    public static String tableName = "tableName";
    public static int size = 10;

    /**
     * 获取redis的链接
     */
    public synchronized ShardedJedis getConnection() {
        return redisPool.getResource();
    }

    /**************商品维度计数（喜欢数，评论数，鉴定数，浏览数,etc）****************/
    @Test
    public void hmset() {
        ShardedJedis shardedJedis = this.getConnection();
//        shardedJedis.hset("product_no"+1,"xihuanshu","2");
        for (int i = 0; i < 15; i++) {
            Map<String, String> map = Maps.newHashMap();
            map.put("xihuanshu", "10");
            map.put("pingluanshu", "20");
            map.put("fangwenshu", "30");
            shardedJedis.hmset("count_product:product_no" + i, map);
        }
        shardedJedis.close();
    }

    @Test
    public void hmsetExist() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            Map<String, String> map = Maps.newHashMap();
            map.put("pingluanshu", "20" + 11);
            shardedJedis.hmset("count_product:product_no" + 10, map);
        }
        shardedJedis.close();
    }

    /**
     * 返回名称为key的hash中field对应的value
     */
    @Test
    public void hmget() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println(shardedJedis.hmget("count_product:product_no10","xihuanshu","pingluanshu","fangwenshu"));
        System.out.println(shardedJedis.hvals("count_product:product_no10"));
        shardedJedis.close();
    }

    /**
     * hvals(key)：返回名称为key的hash中所有键对应的value
     * hkeys(key)：返回名称为key的hash中所有键
     * hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value
     */
    @Test
    public void hvals() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println(shardedJedis.hvals("count_product:product_no10"));
        shardedJedis.close();
    }

    /**
     * 各种数据统计  加减
     */
    @Test
    public void hincrby() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            shardedJedis.hincrBy("count_product:product_no" + i, "fangwenshu", -100);
        }
        shardedJedis.close();
    }


    /**************用户维度计数（动态数、关注数、粉丝数、喜欢商品数、发帖数 等）****************/

    @Test
    public void hmsetUser() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            Map<String, String> map = Maps.newHashMap();
            map.put("dongtaishu", "10");
            map.put("fensishu", "20");
            map.put("guanzhushu", "30");
            shardedJedis.hmset("count_user:user_no" + i, map);
        }
        shardedJedis.close();
    }

    /**************************存储社交关系****************************/

    @Test
    public void zadd() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 100008; i < 100015; i++) {
            Map<String, Double> map = Maps.newHashMap();
            map.put("follow" + i, Double.valueOf(i));
            map.put("fans" + i, Double.valueOf(i));
            map.put("focusOn" + i, Double.valueOf(i));
            shardedJedis.zadd("user_relation:100000", map);
        }
        shardedJedis.close();
    }

    /**
     * 求交集
     * <p>譬如将用戶的好友/粉丝/关注，可以存在一个sorted set中，score
     * 可以是timestamp，这样求两个人的共同好友的操作，可能就只需要用求交集命令即可。
     */
    @Test
    public void zinterstore() {
        ShardedJedis shardedJedis = this.getConnection();
        Collection<Jedis> jedisC = shardedJedis.getAllShards();
        Iterator<Jedis> iter = jedisC.iterator();
        Jedis jedis = iter.next();
        System.out.println(JSON.toJSONString(jedis.zinterstore("user_relation:100003", "user_relation:100001", "user_relation:100000")));
        System.out.println(JSON.toJSONString(shardedJedis.zrange("user_relation:100003", 0, -1)));
        jedis.close();
        shardedJedis.close();
    }


    /**
     * 取最新的N条数据
     * <p>
     * 最近浏览
     */
    @Test
    public void range() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 30; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.lpush("List", i + "")));
        }
        //命令使其永远只保存最近N个ID
        shardedJedis.ltrim("List", 0, 10);
        /**
         * lpop/rpop删除
         */
        System.out.println(JSON.toJSONString(shardedJedis.rpop("List")));

        System.out.println(JSON.toJSONString(shardedJedis.lrange("List", 0, 20)));

        shardedJedis.close();
    }


    @Test
    public void topN() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 10; i++) {
            //System.out.println(JSON.toJSONString(shardedJedis.zadd("topN",Double.valueOf(i+""),"TOP_NO"+i)));
        }
        //修改数据
        //System.out.println(JSON.toJSONString(shardedJedis.zincrby("topN",Double.valueOf("3"),"TOP_NO"+2)));
        //按score进行排序--asc
//        System.out.println(JSON.toJSONString(shardedJedis.zrange("topN", 0, -1)));
        //按score进行排序--dec
//        System.out.println(JSON.toJSONString(shardedJedis.zrevrange("topN", 0, -1)));
        //查询score在min-max之间的值
//        System.out.println(JSON.toJSONString(shardedJedis.zrangeByScore("topN", 2, 9)));

//        System.out.println(JSON.toJSONString(shardedJedis.zrangeWithScores("topN", 2, 9)));
//        System.out.println(JSON.toJSONString(shardedJedis.zrevrangeWithScores("topN", 2, 9)));

//        System.out.println(JSON.toJSONString(shardedJedis.zrangeByScoreWithScores("topN", 2, 9)));
//        System.out.println(JSON.toJSONString(shardedJedis.zrangeByScoreWithScores("topN", 2, 9,1,4)));

//        System.out.println(JSON.toJSONString(shardedJedis.zrem("topN", "")));

        //删除--移除下标 0 至 1 区间内的成员
//        System.out.println(JSON.toJSONString(shardedJedis.zremrangeByRank("topN",0,1)));

        //移除所有薪水在 1500 到 3500 内的员工
        System.out.println(JSON.toJSONString(shardedJedis.zremrangeByScore("topN",0,1)));


        System.out.println(JSON.toJSONString(shardedJedis.zrangeByScoreWithScores("topN", 2, 9)));

//        System.out.println(JSON.toJSONString(shardedJedis.zrange("List",0,20)));
        shardedJedis.close();
    }


}
