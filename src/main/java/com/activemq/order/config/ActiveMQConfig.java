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
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {


    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"failover://tcp://20.20.60.100:61616");
        return  connectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(100);
        pooledConnectionFactory.setConnectionFactory(connectionFactory());
        return pooledConnectionFactory;
    }


    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(pooledConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(1);
        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(cachingConnectionFactory());
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public ActiveMQQueue testQueue(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("spring-topic");
        return activeMQQueue;
    }

    @Bean
    public ActiveMQTopic testTopic(){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("spring-topic");
        return activeMQTopic;
    }

    @Bean
    public QueueListener queueListener(){
        return new QueueListener();
    }

    @Bean
    public Topic1Listener topic1Listener(){
        return new Topic1Listener();
    }

    @Bean
    public Topic2Listener topic2Listener(){
        return new Topic2Listener();
    }

    @Bean
    public DefaultMessageListenerContainer queueContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(cachingConnectionFactory());
        defaultMessageListenerContainer.setDestination(testQueue());
        defaultMessageListenerContainer.setMessageListener(queueListener());
        return defaultMessageListenerContainer;
    }

    @Bean
    public DefaultMessageListenerContainer topic1Container(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(cachingConnectionFactory());
        defaultMessageListenerContainer.setDestination(testTopic());
        defaultMessageListenerContainer.setMessageListener(topic1Listener());
        return defaultMessageListenerContainer;
    }

    @Bean
    public DefaultMessageListenerContainer topic2Container(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(cachingConnectionFactory());
        defaultMessageListenerContainer.setDestination(testTopic());
        defaultMessageListenerContainer.setMessageListener(topic2Listener());
        return defaultMessageListenerContainer;
    }





}
