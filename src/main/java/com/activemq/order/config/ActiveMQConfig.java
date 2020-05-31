package com.activemq.order.config;




import com.activemq.order.spring.QueueListener;
import com.activemq.order.spring.Topic1Listener;
import com.activemq.order.spring.Topic2Listener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {


    /**
     *   定义连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"failover://tcp://20.20.60.100:61616");
//        connectionFactory.setRedeliveryPolicy();
        return  connectionFactory;
    }

    /**
     *  池化连接工厂
     * @return
     */
//    @Bean
//    public PooledConnectionFactory pooledConnectionFactory(){
//        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
//        // 设置最大连接数
//        pooledConnectionFactory.setMaxConnections(1000);
//        // 池化连接工厂
//        pooledConnectionFactory.setConnectionFactory(connectionFactory());
//        return pooledConnectionFactory;
//    }


    /**
     *  连接缓存工厂
     * @return
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        // 缓存连接工厂池
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
        // 设置缓存Session大小
        cachingConnectionFactory.setSessionCacheSize(1);
        return cachingConnectionFactory;
    }

    /**
     *  Spring 对JMS接口进一步封装，解决冗长重复代码
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setQosSettings();
        // 设置连接缓存工厂
        jmsTemplate.setConnectionFactory(connectionFactory());
        // 配置简单消息转换器
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    /**
     *  创建Destination（目的地-队列）
     * @return
     */
    @Bean
    public ActiveMQQueue testQueue(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("spring-queue");

        return activeMQQueue;
    }

    /**
     *  创建Destination（目的地-pub/sub）
     * @return
     */
    @Bean
    public ActiveMQTopic testTopic(){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("spring-topic");
        return activeMQTopic;
    }

    /**
     *  (p2p)异步接收消息监听类，MessageListener的实现类
     * @return
     */
    @Bean
    public QueueListener queueListener(){
        return new QueueListener();
    }

    /**
     *  (pub/sub)异步接收消息监听类，MessageListener的实现类
     * @return
     */
    @Bean
    public Topic1Listener topic1Listener(){
        return new Topic1Listener();
    }

    /**
     *  (pub/sub)异步接收消息监听类，MessageListener的实现类
     * @return
     */
    @Bean
    public Topic2Listener topic2Listener(){
        return new Topic2Listener();
    }

    /**
     *  用于将消息监听器绑定到具体的消息目的地上
     * @return
     */
    public DefaultMessageListenerContainer queueContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        // 设置连接缓存工厂
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        // 设置队列
        defaultMessageListenerContainer.setDestination(testQueue());
        // 设置异步消息监听类
        defaultMessageListenerContainer.setMessageListener(queueListener());
//        defaultMessageListenerContainer.setBackOff();

        return defaultMessageListenerContainer;
    }

    /**
     *  用于将消息监听器绑定到具体的消息目的地上
     * @return
     */
    @Bean
    public DefaultMessageListenerContainer topic1Container(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        // 设置连接缓存工厂
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        // 设置sub/pub
        defaultMessageListenerContainer.setDestination(testTopic());
        // 设置异步消息消费类
        defaultMessageListenerContainer.setMessageListener(topic1Listener());
        return defaultMessageListenerContainer;
    }
    /**
     *  用于将消息监听器绑定到具体的消息目的地上
     * @return
     */
    @Bean
    public DefaultMessageListenerContainer topic2Container(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        // 设置连接缓存工厂
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        // 设置sub/pub
        defaultMessageListenerContainer.setDestination(testTopic());
        // 设置异步消息监听类
        defaultMessageListenerContainer.setMessageListener(topic2Listener());
        return defaultMessageListenerContainer;
    }





}
