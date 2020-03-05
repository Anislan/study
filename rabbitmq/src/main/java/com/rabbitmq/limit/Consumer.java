package com.rabbitmq.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {

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

        String exchangeName = "test_qos_exchange";
        String queueName = "test_qos_queue";
        String routingKey = "qos.#";

        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 限流方式，autoACK设置为false
        channel.basicQos(0,1,false);
        channel.basicConsume(queueName,false,new MyConsumer(channel));


    }
}
