package com.redis.transfer;

import cn.hutool.core.lang.Singleton;
import com.redis.transfer.pool.JedisResourceFactory;
import com.redis.transfer.pool.JedisServer;
import com.redis.transfer.pool.imp.JedisPoolResourceFactory;
import com.redis.transfer.services.transfer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferApplicationTests {

    @Autowired
    @Qualifier("JedisPool1")
    JedisResourceFactory jedisResourceFactory;

    @Autowired
    transfer transfer;

    @Test
    public void contextLoads() {
        Jedis resource = jedisResourceFactory.getResource();

        transfer.pass(2001,2002,800.0);
        transfer.pass(2005,2009,909.2);
    }
}
