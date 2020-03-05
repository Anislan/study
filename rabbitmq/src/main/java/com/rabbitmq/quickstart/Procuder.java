package com.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 */
public class Procuder {

    public static void main(String[] args) throws Exception{

        //1 创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.144.129");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3 通过Connection创建一个Channel
        Channel channel=connection.createChannel();

        //4 通过Channel发送数据
        for (int i=0; i< 5; i++) {
            String msg = String.format("%s-%s","hello RabbitMQ",String.valueOf(i));
            channel.basicPublish("","test001",null,msg.getBytes());
        }

        //5 关闭相关连接
        channel.close();
        connection.close();

    }

}
