package com.springboot.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

//@Configuration
public class JedisConfig {

//    @Bean
    public Jedis jedis(){
        //连接redis服务器，192.168.0.100:6379
        Jedis jedis = new Jedis("123.57.33.87", 6379);
        // //权限认证
        jedis.auth("123456");
        // 操作单独的文本串
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1000);
        //最大空闲时间
        config.setMaxWaitMillis(1000);
        // 最大等待时间
        config.setMaxTotal(500);
        // redis池中最大对象个数
        JedisShardInfo  sharInfo = new JedisShardInfo("123.57.33.87", 6379);
        sharInfo.setPassword("123456");
        sharInfo.setConnectionTimeout(5000);
        //链接超时时间
        jedis = new Jedis(sharInfo);
        return jedis;
    }

}
