package com.rabbitmq.dlx;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws  Exception{
        // 1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.144.130");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2 获取连接
        Connection connection=connectionFactory.newConnection();

        // 3 获取Channel,声明一个普通的交换机和队列以及路由
        Channel channel=connection.createChannel();
        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";

        channel.exchangeDeclare(exchangeName,"topic",true,false,null);

        Map<String,Object> agruments = new HashMap<>();
        String dlxExchangeName = "dlx.echange";
        agruments.put("x-dead-letter-exchange",dlxExchangeName);
        // 这个aruments属性，要设置到声明的队列上
        channel.queueDeclare(queueName,true,false,false,agruments);
        channel.queueBind(queueName,exchangeName,routingKey);

        // 要进行死信队列的声明
        channel.exchangeDeclare(dlxExchangeName,"topic",true,false,null);
        channel.queueDeclare("dlx.queue",true,false,false,null);
        channel.queueBind("dlx.queue",dlxExchangeName,"#");

        channel.basicConsume(queueName,false,new MyConsumer(channel));



    }
}
