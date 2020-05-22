package com.activemq.order.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *  消费者异步消费消息
 */

public class QueueListener  implements MessageListener{


    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
               TextMessage textMessage = (TextMessage) message;
                String messageStr = textMessage.getText();
                System.out.println("生产者投递消息到MQ成功,消息内容为:"+messageStr);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else {
            throw  new IllegalArgumentException("只支持TextMessage类型消息");
        }
    }
}
