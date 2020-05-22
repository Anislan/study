package com.activemq.order.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息传送模型：发布订阅模型（Pub/Sub）
 */
public class Comsumer {

    // 默认用户
    public static final String USERNAME= ActiveMQConnection.DEFAULT_USER;

    // 默认密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    // ActiveMQ服务器
    public static final String BROKER_URL = "failover:tcp://20.20.60.100:61616";

    public static void main(String[] args) {
            // 创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
            try {
                // 创建连接
                Connection connection = connectionFactory.createConnection();
                // 开启连接
                connection.start();
                // 创建会话,不需要事务
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                // 创建 Queue,用作消费者
                Queue queue1 = session.createQueue("queue1");
                // 消息消费者1
                MessageConsumer messageConsumer1 = session.createConsumer(queue1);
                messageConsumer1.setMessageListener((Message message) -> {
                    try {
                        System.out.println("消费者1 接收到消息:" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });
                // 关闭资源
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }

    }

}
