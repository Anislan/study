package com.springboot.learn;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingRedisApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedisApplication.class);





    public static void main(String[] args) throws InterruptedException {

     SpringApplication.run(MessagingRedisApplication.class, args);

    }


}
