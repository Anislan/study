package com.springboot.learn.service.impl;

import com.springboot.learn.domain.IqProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Service
public class RedisServiceImpl {

//    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
    private Jedis jedis;

    public void setStr(String key, Object value) {
        setStr(key, value, null);
    }

    public void setStr(String key, Object value, Long time) {
        if (value == null) {
            return;
        }
        if (value instanceof String) {
            String obj = (String) value;
            stringRedisTemplate.opsForValue().set(key, obj);
        } else if (value instanceof List) {
            stringRedisTemplate.opsForList().leftPush(key,value.toString());
        } else if (value instanceof Map) {
            Map obj = (Map) value;
            stringRedisTemplate.opsForHash().putAll(key, obj);
        }
        if (time != null)
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public Object getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean del(String key) {
        return jedis.del(key.getBytes()) > 0;
    }
}
