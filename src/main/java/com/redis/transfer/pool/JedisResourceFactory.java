package com.redis.transfer.pool;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wrmu
 */
public class JedisResourceFactory  implements com.redis.transfer.pool.imp.JedisPoolResourceFactory, DisposableBean {
    private static final int DEFAULT_PORT = 6379;
    private static final String HOST= "192.168.229.129";


    private JPConfig jedisPoolConfig;

    private String host;
    private int port;
    private JedisPool jedisPool;

    public JedisResourceFactory( JPConfig jedisPoolConfig,String host) {
        this(jedisPoolConfig,host, DEFAULT_PORT);
    }

    private JedisResourceFactory(JPConfig jedisPoolConfig, String host, int port) {
        this.jedisPoolConfig = jedisPoolConfig;
        this.host = host;
        this.port = port;
        init();
    }

    private void init(){
        this.jedisPool=new JedisPool(this.jedisPoolConfig,this.host,this.port);
    }

    @Override
    public Jedis getResource(){
        return jedisPool.getResource();
    }

    @Override
    public void destroy(){
        jedisPool.close();
    }

}
