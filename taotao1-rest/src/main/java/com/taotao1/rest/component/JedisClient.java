package com.taotao1.rest.component;

/**
 * Created by shenchao on 2016/12/27.
 */
public interface JedisClient {
    public String set(String key, String value);

    public String get(String key);

    public Long hset(String key, String item, String value);

    public String hget(String key, String item);

    public Long incr(String key);

    public Long decr(String key);

    public Long expire(String key, int time);

    public Long persist(String key);

    public Long ttl(String key);

    public Long hdel(String key,String item);
}
