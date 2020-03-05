package com.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer4TopicExchange {

    public static void main(String[] args) throws  Exception {
        // 1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.144.129");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        // 2 创建连接
        Connection connection=connectionFactory.newConnection();

        // 3 创建Channel
        Channel channel=connection.createChannel();

        // 4 声明
        String exchangeName ="test_fanout_exchange";
        String exchangeType ="fanout";
        String queueName ="test_fanout_queue";
        String routingKey ="";

        // 声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,true,false,null);
        // 声明队列
        channel.queueDeclare(queueName,false,false,false,null);
        // 建立交换机和队列绑定
        channel.queueBind(queueName,exchangeName,routingKey);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery=queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息："+msg);
        }


    }
}
