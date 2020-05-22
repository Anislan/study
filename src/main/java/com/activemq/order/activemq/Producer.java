package com.activemq.order.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 *  消息传送模型:p2p
 */
public class Producer {

    // 默认用户
    public static final String USERNAME= ActiveMQConnection.DEFAULT_USER;

    // 默认密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    // ActiveMQ服务器
    public static final String BROKER_URL = "failover://tcp://20.20.60.100:61616";


    public static void main(String[] args) {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
        try{
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();
            // 创建会话,不需要事务
            Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            // 创建 queue,
            Queue queue = session.createQueue("queue1");
            // 消息生产者
            ActiveMQMessageProducer producer = (ActiveMQMessageProducer)session.createProducer(queue);
            for (int i = 0; i < 3; i++) {
                TextMessage message = session.createTextMessage("queue发送消息"+i);
                producer.send(queue, message,new ProducerSendCompleteNotify());
//                producer.send(queue,message);
            }

            // 关闭资源
            session.commit();
            session.close();
            connection.close();

            Thread.sleep(5000);
        }catch (JMSException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
