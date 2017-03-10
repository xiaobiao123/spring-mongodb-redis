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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

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
//    private Jedis jedis;

    public static String dbName="dbName";
    public static String tableName="tableName";
    public static int size=10;
    /**
     * 获取redis的链接
     */
    public synchronized ShardedJedis getConnection() {
        return redisPool.getResource();
    }
    /**
     * reddis string类型新增
     */
    @Test
    public void setString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i <10 ; i++) {
            shardedJedis.set("com:name",i+"");
        }
         shardedJedis.close();
    }
    /**
     * reddis string类型获取
     */
    @Test
    public void getString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i <10 ; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.get("com:name")));
        }
        shardedJedis.close();
    }
    /**
     * 删除string类型
     */
    @Test
    public void delString() {
        ShardedJedis shardedJedis = this.getConnection();
        for (int i = 0; i <10 ; i++) {
            System.out.println(JSON.toJSONString(shardedJedis.del("com:name")));
        }
        shardedJedis.close();
    }


    /**
     * 新增list
     */
    @Test
    public void lpushList() {
        ShardedJedis shardedJedis = this.getConnection();

        for (int i = 0; i <10 ; i++) {
            User user =new User();
            user.setNickname("nickname"+i);
            user.setState(i);
            user.setId(i);
            //插入输入
            shardedJedis.lpush("user",JSON.toJSONString(user));
        }
        //获取对象的长度
        System.out.println("====================="+shardedJedis.llen("user"));


        shardedJedis.close();
    }


    @Test
    public void lindex() {
        ShardedJedis shardedJedis = this.getConnection();

        //获取对象的长度
        System.out.println("====================="+shardedJedis.llen("user"));

        System.out.println("====================="+JSON.toJSONString(shardedJedis.lindex("user",7)));
        shardedJedis.close();
    }












    /**
     * 从redis库，获取单个UUID主键
     *
     * @author: yucx
     * @version: 2016年5月11日 上午2:03:12
     */
    @Test
    public void genUuidDbKey() {
        Long tableKeyId = null;
        ShardedJedis shardedJedis = this.getConnection();
        try {
            tableKeyId = shardedJedis.incr( dbName+ "." + tableName + ".KEY");
            System.out.println(JSON.toJSONString("============="+tableKeyId));
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
     */
    @Test
    public void genMultiUuidDbKey( ) {
        List<Long> ids = new ArrayList<Long>();
        ShardedJedis shardedJedis = this.getConnection();
        try {
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

            System.out.println("=================="+JSON.toJSONString(ids));
        } catch (Exception e) {
            logger.error("调用genMultiUuidDbKey方法异常:   " + e.getCause().toString());
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }


}
