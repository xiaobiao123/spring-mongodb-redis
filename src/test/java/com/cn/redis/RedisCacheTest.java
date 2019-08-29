package com.cn.redis;

import cn.springmvc.redis.RedisCache;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext-redis.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisCacheTest {

    private static Logger logger = Logger.getLogger(RedisCacheTest.class);

    @Autowired
    private RedisCache redisCache;


}
