package com.taotao1.sso.component.impl;

import com.taotao1.sso.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * redis集群版实现类
 * Created by shenchao on 2016/12/27.
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster cluster;

    @Override
    public String set(String key, String value) {
        return cluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return cluster.get(key);
    }

    @Override
    public Long hset(String key, String item, String value) {
        return cluster.hset(key, item, value);
    }

    @Override
    public String hget(String key, String item) {
        return cluster.hget(key,item);
    }

    @Override
    public Long incr(String key) {
        return cluster.incr(key);
    }

    @Override
    public Long decr(String key) {
        return cluster.decr(key);
    }

    @Override
    public Long expire(String key, int time) {
        return cluster.expire(key,time);
    }

    @Override
    public Long persist(String key) {
        return cluster.persist(key);
    }

    @Override
    public Long ttl(String key) {
        return cluster.ttl(key);
    }

    @Override
    public Long hdel(String key, String item) {
        return cluster.hdel(key, item);
    }
}
