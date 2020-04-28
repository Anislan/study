package com.springboot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class LearnApplication {

	public static void main(String[] args) {

		SpringApplication.run(LearnApplication.class, args);
		Jedis jedis = new Jedis("123.57.33.87", 6379);
		// //权限认证
		jedis.auth("123456");
		System.out.println(jedis.ping());
	}

}
