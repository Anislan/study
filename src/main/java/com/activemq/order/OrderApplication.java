package com.activemq.order;

import com.activemq.order.spring.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OrderApplication {



	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
		log.info("订单服务已启动");
	}

}
