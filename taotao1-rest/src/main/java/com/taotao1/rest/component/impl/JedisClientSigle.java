package com.taotao1.rest.component.impl;

import com.taotao1.rest.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版实现类
 * Created by shenchao on 2016/12/27.
 */
public class JedisClientSigle implements JedisClient {

    @Autowired
    public JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis resource = jedisPool.getResource();
        String result = resource.set(key, value);
        resource.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;
    }

    @Override
    public Long hset(String key, String item, String value) {
        Jedis resource = jedisPool.getResource();
        Long hset = resource.hset(key, item, value);
        resource.close();
        return hset;
    }

    @Override
    public String hget(String key, String item) {
        Jedis resource = jedisPool.getResource();
        String s = resource.hget(key, item);
        resource.close();
        return s;
    }

    @Override
    public Long incr(String key) {
        Jedis resource = jedisPool.getResource();
        Long incr = resource.incr(key);
        resource.close();
        return incr;
    }

    @Override
    public Long decr(String key) {
        Jedis resource = jedisPool.getResource();
        Long decr = resource.decr(key);
        resource.close();
        return decr;
    }

    @Override
    public Long expire(String key, int time) {
        Jedis resource = jedisPool.getResource();
        Long expire = resource.expire(key, time);
        resource.close();
        return expire;
    }

    @Override
    public Long persist(String key) {
        Jedis resource = jedisPool.getResource();
        Long persist = resource.persist(key);
        resource.close();
        return persist;
    }

    @Override
    public Long ttl(String key) {
        Jedis resource = jedisPool.getResource();
        Long ttl = resource.ttl(key);
        resource.close();
        return ttl;
    }

    @Override
    public Long hdel(String key, String item) {
        Jedis resource = jedisPool.getResource();
        Long result = resource.hdel(key,item);
        resource.close();
        return result;
    }
}
