package redis;

import cn.springmvc.model.User;
import cn.springmvc.redis.RedisLock;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.*;

import java.util.*;

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
     * redis 分布式锁
     */
    @Test
    public void redsiLock() {
        RedisLock redisLock = new RedisLock("test", redisPool);

        Boolean lock = redisLock.lock(120l);

        //RedisLock redisLock2 = new RedisLock("test", redisPool);
        //
        //Boolean lock2 = redisLock2.lock(120l);
        //
        //System.out.println(lock+"lock"+"lock2:"+lock2);

        if (lock) {
            // doSomething.........................
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("cccccccccccccccccc");
            } finally {
                System.out.println("finally 锁已经被释放");
                redisLock.unlock();
            }

            System.out.println("锁已经被释放");
            //执行完后删除锁

        }


    }


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
        //Transaction transaction=jedis.multi();
        for (int i = 0; i < 3; i++) {
            shardedJedis.set("redis:string" + i, i + "");
        }

        //nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
        //expx expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
        //过期时间，单位是expx所代表的单位。

        shardedJedis.setex("redis:string", 1000, "xxxxxxxxxx");
        shardedJedis.close();
        //设置存活时间
        //shardedJedis.expire()
        //shardedJedis.setex(key,value)
        //shardedJedis.setnx(key,value) //当仅当SETNX 是SET if Not eXists的简写
        // 返回整数，具体为
        //    - 1，当 key 的值被设置
        //    - 0，当 key 的值没被设置
    }

    /**
     * reddis string类型获取
     */
    @Test
    public void getString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 1; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.get("redis:string" + i)));
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
            System.out.println(JSON.toJSONString(shardedJedis.del("redis:string" + i)));
        }
        shardedJedis.close();

    }

    /**
     * string incr +/decr -1
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
     * <p>
     * <p>Redis 管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应。</p>
     */
    @Test
    public void genMultiUuidDbKey() {
        List<Long> ids = new ArrayList<Long>();

        ShardedJedis shardedJedis = this.getConnection();
        ShardedJedis shardedJedis1 = this.getConnection();
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


    /*********************************************string操作 end*******************************************/

    /*********************************************hash 操作 start*******************************************/
    /**
     * 新增hash
     */
    @Test
    public void addHash() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hset("redis:hash", "hashField" + i, "hashValue" + i +
                    "update")));
        }
        shardedJedis.close();
    }

    /**
     * 获取hash
     */
    @Test
    public void getHash() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hget("redis:hash", "hashField" + i)));
        }
        shardedJedis.hgetAll("redis:hash");
        shardedJedis.close();
    }

    /**
     * 删除hash
     */
    @Test
    public void delHash() {
        ShardedJedis shardedJedis = this.getConnection();
        shardedJedis.hmget("redis:hash", "hashField", "hashField");
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.hdel("redis:hash", "hashField" + i)));
        }
        shardedJedis.close();
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在。
     */
    @Test
    public void hexistsHash() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println(shardedJedis.hexists("redis:hash", "hashField1"));
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
            shardedJedis.lpush("redis:list", JSON.toJSONString(user));
        }

        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
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
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setNickname("nickname" + i);
            user.setState(i);
            user.setId(i);
            shardedJedis.lrem("redis:list", 3, JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
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
            shardedJedis.rpush("redis:list", JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
        shardedJedis.close();
    }

    /**
     * 删除 从前 LPOP key移除并返回列表 key 的头元素
     */
    @Test
    public void lpopList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lpop("redis:list"));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));

        //会阻塞住直到消息到来
        //shardedJedis.blpop("redis:list");

        shardedJedis.close();
    }

    /**
     * RPOP key移除并返回列表 key 的尾元素
     */
    @Test
    public void rpopList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.rpop("redis:list"));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
        shardedJedis.close();
    }


    /**
     * lrangeList（分页查询）
     */
    @Test
    public void lrangeList() {
        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lrange("redis:list", 0, 10));

        //命令使其永远只保存最近N个ID
        shardedJedis.ltrim("redis:list", 0, 9);

        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
        shardedJedis.close();
    }

    /**
     * 删除list
     */
    @Test
    public void lremList() {

        User user = new User();
        user.setNickname("nickname" + 7);
        user.setState(7);
        user.setId(7);

        ShardedJedis shardedJedis = this.getConnection();
        System.out.println("=====================" + shardedJedis.lrem("redis:list", 1, JSON.toJSONString(user)));
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
        shardedJedis.close();
    }

    /**
     * 命令用于获取在存储于列表的key索引的元素
     */
    @Test
    public void lindex() {
        ShardedJedis shardedJedis = this.getConnection();
        //获取对象的长度
        System.out.println("=====================" + shardedJedis.llen("redis:list"));
        System.out.println("=====================" + JSON.toJSONString(shardedJedis.lindex("redis:list", 0)));
        shardedJedis.close();

    }


    /********************************set 集合******************************************/

    /**
     * 添加set
     */
    @Test
    public void sadd() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i < 10; i++) {
            //添加
            System.out.println("=====================" + shardedJedis.sadd("redis:set", "member" + i, "member" + 2 * i));
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
        System.out.println("=====================" + shardedJedis.scard("redis:set"));
        //随机返回
        System.out.println("=====================" + JSON.toJSONString(shardedJedis.srandmember("redis:set", 2)));

        shardedJedis.close();
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     */
    @Test
    public void sismember() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.sismember("redis:set", "member" + 1));
        shardedJedis.close();
    }


    /**
     * smembers(key) ：返回名称为key的set的所有元素
     */
    @Test
    public void smembers() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.smembers("redis:set"));

        shardedJedis.close();
    }

    /**
     * 移除集合中一个或多个成员
     */
    @Test
    public void srem() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.srem("redis:set", "member" + 2));
        System.out.println("=====================" + shardedJedis.scard("redis:set"));
        shardedJedis.close();
    }

    /******************************** 有序集合(sorted set) ******************************************/

    @Test
    public void zadd() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("=====================" + shardedJedis.zadd("redis:sort:set", random.nextInt(100),
                    "test"
                            + i));
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
            System.out.println("=====================" + shardedJedis.zrem("redis:sort:set", "test" + i));
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
        System.out.println("=====================" + shardedJedis.zcard("redis:sort:set"));
        shardedJedis.close();
    }

    /**
     * 获取对象的值
     */
    @Test
    public void zscore() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zscore("redis:sort:set", "test4"));
        shardedJedis.close();
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员--正序
     */
    @Test
    public void zrange() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zrange("redis:sort:set", 0, -1));
        shardedJedis.close();
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员--倒序
     */
    @Test
    public void zrevrange() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.zrevrange("redis:sort:set", 0, -1));
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

        Set<Tuple> set = shardedJedis.zrangeWithScores("redis:sort:set", 0, 9);
        for (Tuple tuple : set) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        System.out.println("===================================================");
        Set<Tuple> set1 = shardedJedis.zrevrangeWithScores("redis:sort:set", 0, -1);
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
        Set<Tuple> set = shardedJedis.zrangeByScoreWithScores("redis:sort:set", 0, 39);
        for (Tuple tuple : set) {
            System.out.println("=====================" + tuple.getScore());
            System.out.println("=====================" + tuple.getElement());
        }
        System.out.println("===================================================");

        Set<Tuple> set1 = shardedJedis.zrevrangeByScoreWithScores("redis:sort:set", 3, -1);
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
        System.out.println("=====================" + shardedJedis.zcount("redis:sort:set", 5, 40));
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
        System.out.println("=====================" + shardedJedis.zincrby("redis:sort:set", 10, "test6"));

        shardedJedis.close();
    }

    /*********************************************************/
    /**
     * HyperLoglog-基数统计
     * <p>
     * 基数(不重复元素)
     */
    @Test
    public void pfadd() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        System.out.println("=====================" + shardedJedis.pfadd("redis:pfadd", "test6"));
        shardedJedis.pfcount("redis:pfadd");
        shardedJedis.close();
    }

    @Test
    public void GEOADD() {
        ShardedJedis shardedJedis = this.getConnection();
        //添加
        //System.out.println("=====================" + shardedJedis.ge("redis:pfadd", "test6"));
        double ss = -85.05112878d;
        double dd = 39.98448618d;
        shardedJedis.geoadd("redis:geoadd", ss, dd, "xxxxxxxxxxxx");
        shardedJedis.geoadd("redis:geoadd", ss, dd, "sss");
        System.out.println(JSONObject.toJSONString(shardedJedis.geodist("redis:geoadd", "xxxxxxxxxxxx", "sss")));
        shardedJedis.close();
    }

    /***********************************************/

//    https://blog.csdn.net/fly910905/article/details/82629687
//
//            # 计算出 7 天都在线的用户
//    BITOP "AND" "7_days_both_online_users" "day_1_online_users" "day_2_online_users" ... "day_7_online_users"
//
//            # 计算出 7 在的在线用户总人数
//    BITOP "OR" "7_days_total_online_users" "day_1_online_users" "day_2_online_users" ... "day_7_online_users"
//
//            # 计算出两天当中只有其中一天在线的用户
//    BITOP "XOR" "only_one_day_online" "day_1_online_users" "day_2_online_users"
    /**
     * 统计在线用户数
     */
    @Test
    public void bitset() {
        ShardedJedis shardedJedis = this.getConnection();
        //key
        String key = String.format("sid:%08d", 2);
        //用户id
        int cid = new Random().nextInt(100000);
        System.out.println("setbit = " + key + ":" + cid);
        // true 在线 false下线
        shardedJedis.setbit(key, cid, true);
        //是否在线
        shardedJedis.getbit(key, cid);
        //用户在线数
        shardedJedis.bitcount(key);
        shardedJedis.close();
    }

    /**
     * 用户签到
     */
    @Test
    public void bitsetmark() {
        ShardedJedis shardedJedis = this.getConnection();
        //key
        String key = String.format("sid:%08d", 2);
        //签到时间
        int cid = new Random().nextInt(100000);
        System.out.println("setbit = " + key + ":" + cid);
        // true 在线 false下线
        shardedJedis.setbit(key, cid, true);
        //是否在线
        shardedJedis.getbit(key, cid);
        //用户在线数
        shardedJedis.bitcount(key);
        shardedJedis.close();
    }

    /**
     * 统计活跃用户量
     * <p>
     * 统计某年某月某日的用户活跃量
     */
    @Test
    public void bitop() {

        Collection<Jedis> allShards = redisPool.getResource().getAllShards();

        allShards.isEmpty();

        Jedis next = allShards.iterator().next();

           /*
            BITOP AND destkey key [key ...] ，对一个或多个 key 求逻辑并，并将结果保存到 destkey 。
            BITOP OR destkey key [key ...] ，对一个或多个 key 求逻辑或，并将结果保存到 destkey 。
            BITOP XOR destkey key [key ...] ，对一个或多个 key 求逻辑异或，并将结果保存到 destkey 。
            BITOP NOT destkey key ，对给定 key 求逻辑非，并将结果保存到 destkey 。
         */
        jedis.setbit("bitop1", 0, true);
        jedis.setbit("bitop1", 1, true);
        jedis.setbit("bitop0", 0, false);
        jedis.setbit("bitop0", 1, false);


        jedis.bitop(BitOP.AND, "AND", "bitop1", "bitop0");


        jedis.bitop(BitOP.AND, "SSS", "stat_2017-01-10", "stat_2017-01-11", "stat_2017-01-12");
        //总数
        jedis.bitcount("SSS");

    }

}
