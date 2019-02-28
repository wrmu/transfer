package com.redis.transfer.services;

import com.redis.transfer.pool.JedisResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class transfer {

    @Autowired
    @Qualifier("jedisPool1")
    JedisResourceFactory jedisResourceFactory;


    public String pass(Integer id1,Integer id2,Double count){
        Jedis resource = jedisResourceFactory.getResource();
        String s = resource.get(String.valueOf(id1));
        String vo = resource.get(String.valueOf(id2));
        Double vou = Double.valueOf(vo);
        Double cou=Double.valueOf(s);
        if (cou>=count){
            Double v = cou - count;
            Double n=  vou+count;
            resource.set(id1.toString(),v.toString());
            resource.set(id2.toString(),n.toString());
            return "转账成功，共转出："+count;
        }else {
            return "账户余额不足，转帐失败";
        }

    }

}
