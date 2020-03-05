package com.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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

       // 4 指定消息投递的模式：消息的确认模式
        channel.confirmSelect();

        // 5 发送一条消息
        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";
        String msg = "Hello RabbitMQ Send confirm message!";

    }

}
