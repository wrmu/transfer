package com.redis.transfer.pool;

import com.redis.transfer.pool.imp.JedisPoolResourceFactory;
import redis.clients.jedis.Jedis;

public class JedisServer {
    JedisPoolResourceFactory jedisPoolResourceFactory;

    public JedisServer(JedisPoolResourceFactory jedisPoolResourceFactory) {
        this.jedisPoolResourceFactory = jedisPoolResourceFactory;
    }

    abstract class Excutor<T> {
        Jedis jedis;

        public Excutor(JedisPoolResourceFactory jedisPoolResourceFactory) {
            this.jedis = jedisPoolResourceFactory.getResource();

        }

        abstract T execute();

        public T getResult() {
            T result = null;
            try {
                result = execute();
            } catch (Exception e) {
                throw new RuntimeException("Redis execte Error", e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                    jedis = null;
                }
            }
            return result;
        }

        public long lpushStrings(final String key, final String[] values) {
            return new Excutor<Long>(jedisPoolResourceFactory) {
                @Override
                Long execute() {
                    return jedis.lpush(key, values);
                }
            }.getResult();
        }
    }
}
