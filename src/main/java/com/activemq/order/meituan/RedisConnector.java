package com.activemq.order.meituan;


import redis.clients.jedis.Jedis;

/**
 *  连接redis服务器
 */
public class RedisConnector {

    public static void main(String[] args) {
       Jedis jedis = new Jedis("####", 60379);  //指定Redis服务Host和port
        jedis.auth("####"); //如果Redis服务连接需要密码，制定密码
        String value = jedis.get("a"); //访问Redis服务
        System.out.println(value);
        jedis.close(); //使用完关闭连接
    }
}
