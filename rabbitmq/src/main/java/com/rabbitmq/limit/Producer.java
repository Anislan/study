package com.rabbitmq.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws  Exception{
        // 1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.144.130");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2 获取连接
        Connection connection=connectionFactory.newConnection();

        // 3 获取Channel
        Channel channel=connection.createChannel();
        String exchange = "test_qos_exchange";
        String routingKey = "qos.save";
        for(int i =0; i<5; i ++){
            String msg = "Hello RabbitMQ QOS Message" + i;
            channel.basicPublish(exchange, routingKey, true, null, msg.getBytes());
        }
    }
}
