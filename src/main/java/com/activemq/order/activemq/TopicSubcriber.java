package com.activemq.order.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息传送模型：发布订阅模型（Pub/Sub）
 */
public class TopicSubcriber {

    // 默认用户
    public static final String USERNAME= ActiveMQConnection.DEFAULT_USER;

    // 默认密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    // ActiveMQ服务器
    public static final String BROKER_URL = "failover:tcp://20.20.60.100:61616";

    public static void main(String[] args) {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
        try{
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();
            // 创建会话,不需要事务
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            // 创建 Topic,用作消费者订阅消息
            Topic topic = session.createTopic("activemq-topic");
            // 消息消费者1
            MessageConsumer messageConsumer1 = session.createConsumer(topic);
            messageConsumer1.setMessageListener((Message message)->{
                try {
                    System.out.println("消费者1 接收到消息:"+((TextMessage)message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            // 消息消费者2
            MessageConsumer messageConsumer2 = session.createConsumer(topic);
            messageConsumer2.setMessageListener((Message message)->{
                try {
                    System.out.println("消费者2 接收到消息:"+((TextMessage)message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            //让主线程休眠100秒，使消息消费者对象能继续存活一段时间从而能监听到消息
            Thread.sleep(100 * 1000);

            // 关闭资源
            session.close();
            connection.close();
        }catch (JMSException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
