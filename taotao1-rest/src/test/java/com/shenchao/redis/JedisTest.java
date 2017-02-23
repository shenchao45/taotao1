package com.shenchao.redis;

import com.taotao1.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shenchao on 2016/12/27.
 */
public class JedisTest {
    @Test
    public void testJedisTest() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.187.128", 7001));
        nodes.add(new HostAndPort("192.168.187.128", 7002));
        nodes.add(new HostAndPort("192.168.187.128", 7003));
        nodes.add(new HostAndPort("192.168.187.128", 7004));
        nodes.add(new HostAndPort("192.168.187.128", 7005));
        nodes.add(new HostAndPort("192.168.187.128", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("wzh", "我爱你");
        String wzh = cluster.get("wzh");
        System.out.println(wzh);
    }
    @Test
    public void testJedisStandalongTest() {
        Jedis jedis = new Jedis("192.168.187.128", 6379);
        String set = jedis.set("bbb", "aaaa");
        String age = jedis.get("bbb");
        System.out.println(age);
        jedis.close();
    }
    @Test
    public void testJedisSpringTest() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        JedisClient bean = classPathXmlApplicationContext.getBean(JedisClient.class);
        System.out.println(bean);
    }

}
