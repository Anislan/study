package com.activemq.order.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听类
 */

public class QueueListener  implements MessageListener{


    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
               TextMessage textMessage = (TextMessage) message;
                String messageStr = textMessage.getText();
                System.out.println("队列监听器接收到文本消息:"+messageStr);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else {
            throw  new IllegalArgumentException("只支持TextMessage类型消息");
        }
    }
}
