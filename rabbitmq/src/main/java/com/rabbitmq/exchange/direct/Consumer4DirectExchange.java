package com.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer4DirectExchange {

    public static void main(String[] args) throws Exception, TimeoutException {

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
        String exchangeName= "test_direct_exchange";
        String exchangeType ="direct";
        String queueName ="test_direct_queue";
        String routingKey ="test.direct";

        // 声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,false,false,false,null);
        // 建立一个绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);

        // durable 是否持久化消息
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 参数，队列名称，是否自动ACK，Consumer
        channel.basicConsume(queueName,true,consumer);

        while (true){
            //获取消息，如有没有消息会一直阻塞
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息："+msg);
        }
    }
}
