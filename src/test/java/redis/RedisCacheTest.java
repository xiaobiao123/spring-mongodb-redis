package redis;

import cn.springmvc.redis.RedisCache;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext-redis.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisCacheTest {

    private static Logger logger = Logger.getLogger(RedisCacheTest.class);

    @Autowired
    private RedisCache redisCache;


}
