package redis;

import cn.springmvc.model.User;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext-redis.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisTest {

    private static Logger logger = Logger.getLogger(RedisTest.class);

    /*不支持多命令操作*/
    @Autowired
    private ShardedJedisPool redisPool;
    /*支持多命令操作*/
//    @Autowired
    private Jedis jedis = new Jedis();

    public static String dbName = "test";
    public static String tableName = "tableName";
    public static int size = 10;

    /**
     * 获取redis的链接
     */
    public synchronized ShardedJedis getConnection() {
        return redisPool.getResource();
    }

    /*********************************************string操作 start*******************************************/
    /**
     * reddis string类型新增
     */
    @Test
    public void setString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            shardedJedis.set("test:string" + i, i + "");
        }
        shardedJedis.close();
    }

    /**
     * reddis string类型获取
     */
    @Test
    public void getString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 1; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.get("test:string" + i)));
        }
        shardedJedis.close();
    }

    /**
     * 删除string类型
     */
    @Test
    public void delString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.del("test:string") + 1));
        }
        shardedJedis.close();
    }

    /**
     * string incr /decr
     */
    @Test
    public void genUuidDbKey() {
        Long tableKeyId = null;
        ShardedJedis shardedJedis = this.getConnection();
        try {
            tableKeyId = shardedJedis.incr(dbName + "." + tableName + ".KEY");
            System.out.println(JSON.toJSONString("=============" + tableKeyId));
        } catch (Exception e) {
            logger.error("调用genUuidDbKey方法异常:   " + e.getCause().toString());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    /**
     * 从redis库，获取指定size个数的主键
     *
     * <p>Redis 管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应。</p>
     */
    @Test
    public void genMultiUuidDbKey() {
        List<Long> ids = new ArrayList<Long>();
        ShardedJedis shardedJedis = this.getConnection();
        ShardedJedisPipeline shardedJedisPipeline = shardedJedis.pipelined();
        for (int i = 0; i < size; i++) {
            shardedJedisPipeline.incr(dbName + "." + tableName + ".KEY");
        }
        List<Object> res = shardedJedisPipeline.syncAndReturnAll();
        if (res != null && !res.isEmpty()) {
            for (Object obj : res) {
                ids.add((Long) obj);
            }
        }
        System.out.println("==================" + JSON.toJSONString(ids));
        if (shardedJedis != null) {
            shardedJedis.close();
        }

    }

    @Test
    public void scanString() {
        ShardedJedis shardedJedis = this.getConnection();
//        for (int i = 0; i < 15; i++) {
        shardedJedis.scard("test:string");
//        }
        shardedJedis.close();
    }

    /*********************************************string操作 end*******************************************/

    /*********************************************hash 操作 start*******************************************/
    /**
     * 新增hash
     */
    @Test
    public void addHash() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hset("test:hash", "hashField" + i, "hashValue" + i+"update")));
        }
        shardedJedis.close();
    }

    /**
     * 获取hash
     */
    @Test
    public void getHash() {
        ShardedJedis shardedJedis = this.getConnection();
        //shardedJedis.hmget();

        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hget("test:hash", "hashField" + i)));
        }
        shardedJedis.close();
    }

    /**
     * 删除hash
     */
    @Test
    public void delHash() {
        ShardedJedis shardedJedis = this.getConnection();
        shardedJedis.hmget("test:hash", "hashField", "hashField");
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hdel("test:hash", "hashField" + i)));
        }
        shardedJedis.close();
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在。
     */
    @Test
    public void hexistsHash() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println(shardedJedis.hexists("test:hash", "hashField1"));
        shardedJedis.close();
    }
    /*********************************************hash 操作 end*******************************************/


    /*********************************************list操作 start*******************************************/
    /**
     * 新增list   从前面插入
     */
    @Test
    public void lpushList() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 15; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setState(i);
            user.setId(i);
            //插入输入
            shardedJedis.lpush("user_list", JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * 删除原数
     * <p>
     * key 对象    count  删除数量    value 匹配数
     */
    @Test
    public void lrem() {
        ShardedJedis shardedJedis = this.getConnection();
        //插入输入
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setState(i);
            user.setId(i);
            shardedJedis.lrem("user_list", 3, JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * 新增list  从后面插入
     */
    @Test
    public void rpushList() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 20; i < 25; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setState(i);
            user.setId(i);
            //插入输入
            shardedJedis.rpush("user_list", JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * 删除 LPOP key移除并返回列表 key 的头元素
     */
    @Test
    public void lpopList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lpop("user_list"));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * RPOP key移除并返回列表 key 的尾元素
     */
    @Test
    public void rpopList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.rpop("user_list"));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }


    /**
     * lrangeList
     */
    @Test
    public void lrangeList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lrange("user_list", 0, 10));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * 删除list
     */
    @Test
    public void lremList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lrem("user_list", 1, "nickname7"));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        shardedJedis.close();
    }

    /**
     * 命令用于获取在存储于列表的key索引的元素
     */
    @Test
    public void lindex() {
        ShardedJedis shardedJedis = this.getConnection();
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("user_list"));
        System.out.println("=====================" + JSON.toJSONString(shardedJedis.lindex("user_list", 6)));
        shardedJedis.close();
    }


    /********************************set ******************************************/

    /**
     * 添加set
     */
    @Test
    public void sadd() {
        ShardedJedis shardedJedis = this.getConnection();

        for (int i = 0; i < 10; i++) {
            //添加
            System.out.println("=====================" + shardedJedis.sadd("name_set", "member" + i));
        }
        shardedJedis.close();
    }

    /**
     * 获取集合的成员数
     */
    @Test
    public void scard() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.scard("name_set"));
        shardedJedis.close();
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void sismember() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.sismember("name_set", "member" + 1));
        shardedJedis.close();
    }


    /**
     * 返回给定集合之间的差集。不存在的集合 key 将视为空集。
     */
    @Test
    public void smembers() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.smembers("name_set"));
        shardedJedis.close();
    }

    /**
     * 移除集合中一个或多个成员
     */
    @Test
    public void srem() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.srem("name_set", "member" + 1));
        shardedJedis.close();
    }

    /********************************zadd***********************/

    @Test
    public void zadd() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        for (int i = 0; i < 10; i++) {
            System.out.println("=====================" + shardedJedis.zadd("name_zadd", i, "test" + i));
        }
        shardedJedis.close();
    }

    /**
     * 删除一个或多个
     */
    @Test
    public void zrem() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        for (int i = 0; i < 3; i++) {
            System.out.println("=====================" + shardedJedis.zrem("name_zadd", "test" + i));
        }
        shardedJedis.close();
    }

    /**
     * 获取有序集合的成员数
     */
    @Test
    public void zcard() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zcard("name_zadd"));
        shardedJedis.close();
    }

    /**
     * 获取对象的值
     */
    @Test
    public void zscore() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zscore("name_zadd", "test4"));
        shardedJedis.close();
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员--正序
     */
    @Test
    public void zrange() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zrange("name_zadd", 0, -1));
        shardedJedis.close();
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员--倒序
     */
    @Test
    public void zrevrange() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zrevrange("name_zadd", 0, -1));
        shardedJedis.close();
    }

    /**
     * zrangeWithScores/zrevrangeWithScores
     * start-->起始页
     * <p>
     * end--->limit
     */
    @Test
    public void zrangeWithScores() {
        ShardedJedis shardedJedis = this.getConnection();
        //显示整个有序集成员
        //Set<Tuple> set = shardedJedis.zrangeWithScores("name_zadd", 0, -1);

        Set<Tuple> set = shardedJedis.zrangeWithScores("name_zadd", 0, 9);
        for (Tuple tuple : set) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        System.out.println("===================================================");
        Set<Tuple> set1 = shardedJedis.zrevrangeWithScores("name_zadd", 0, -1);
        for (Tuple tuple : set1) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        shardedJedis.close();
    }

    /**
     * zrangeByScoreWithScores/zrevrangeByScoreWithScores
     * scores 在min和max之间的数据
     */
    @Test
    public void zrangeByScoreWithScores() {
        ShardedJedis shardedJedis = this.getConnection();

        //显示整个有序集成员
        Set<Tuple> set = shardedJedis.zrangeByScoreWithScores("name_zadd", 0, 5);
        for (Tuple tuple : set) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        System.out.println("===================================================");

        Set<Tuple> set1 = shardedJedis.zrevrangeByScoreWithScores("name_zadd", 3, -1);
        for (Tuple tuple : set1) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        shardedJedis.close();
    }


    /**
     * 计算在有序集合中指定区间分数的成员数
     */
    @Test
    public void zcount() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zcount("name_zadd", 5, 10));
        shardedJedis.close();
    }

//    @TestSleep
//    public void zlexcount() {
//        ShardedJedis shardedJedis = this.getConnection();
//        //添加
//        System.out.println("=====================" + shardedJedis.zlexcount("name_zadd","1","6"));
//        shardedJedis.close();
//    }

    /**
     * 有序集合中对指定成员的分数加上增量 increment
     */
    @Test
    public void zincrby() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zincrby("name_zadd", 10, "test6"));
        shardedJedis.close();
    }

}
