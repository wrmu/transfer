package com.redis.transfer.pool.imp;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

public interface JedisPoolResourceFactory {
    public Jedis getResource();
}
