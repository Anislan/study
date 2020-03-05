package com.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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
        String exchange = "test_dlx_exchange";
        String routingKey = "dlx.save";

        for(int i =0; i<5; i ++){
            Map<String,Object> headers = new HashMap<>();
            headers.put("num",i);
            String msg = "Hello RabbitMQ DLX Message" + i;
            AMQP.BasicProperties properties=new AMQP.BasicProperties().builder().
                    deliveryMode(2).contentEncoding("UTF-8").expiration("1000").headers(headers).build();
            channel.basicPublish(exchange, routingKey, true, properties, msg.getBytes());
        }
    }
}
