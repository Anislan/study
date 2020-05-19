package com.activemq.order;

import com.activemq.order.spring.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {


	@Autowired
	private MessageService messageService;

	@Test
	void contextLoads() {
		for (int i = 0; i < 15; i++) {
			messageService.sendQueueMessage("我的测试消息"+i);
		}
		messageService.sendTopicMessage("我的测试消息2");
		messageService.sendTopicMessage("我的测试消息3");
	}

}
