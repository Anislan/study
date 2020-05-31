package com.activemq.order.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 使用JMS 模版处理消息的消息服务类
 */
@Service
public class MessageService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "testQueue")
    private Destination testQueue;

    @Resource(name = "testTopic")
    private Destination testTopic;


    private JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate();

    // 向队列发送消息
    public void sendQueueMessage(String messageContent) {
        jmsTemplate.send(testQueue,
                (Session session) -> {
                    TextMessage message = session.createTextMessage();
                    // 设置消息内容
                    message.setText(messageContent);
                    return message;
                }

        );

        // 保存消息到数据库吗
    }

    // 向主题发送消息
    public void sendTopicMessage(String messageContent) {
        jmsTemplate.send(testTopic, (Session session) -> {
            TextMessage message = session.createTextMessage();
            // 设置消息内容
            message.setText(messageContent);
            return message;
        });
    }


    // 消费消息(同步的消费消息)
    public  void reciveQueueMessage() throws JMSException {
        TextMessage message =(TextMessage)jmsTemplate.receive(testQueue);
        System.out.println(message.getText());
    }
}
