package com.activemq.order.activemq;


import org.apache.activemq.AsyncCallback;
import javax.jms.JMSException;


public class ProducerSendCompleteNotify implements AsyncCallback {


    @Override
    public void onSuccess() {
        System.out.println("消息投递成功！");
    }

    @Override
    public void onException(JMSException exception) {
        exception.printStackTrace();
    }
}
