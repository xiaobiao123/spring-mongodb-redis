package com.tiantian.springintejms.service.impl;

import com.tiantian.springintejms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    //public void sendMessage(Destination destination, final String message) {
    //    System.out.println("---------------生产者发送消息-----------------");
    //    System.out.println("---------------生产者发了一个消息：" + message);
    //    jmsTemplate.send(destination, new MessageCreator() {
    //        public Message createMessage(Session session) throws JMSException {
    //            return session.createTextMessage(message);
    //        }
    //    });
    //}

    @Autowired
    @Qualifier("responseQueue")
    private Destination responseDestination;

    @Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                //持久化和非持久化
                textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                textMessage.setJMSMessageID("消息标示，唯一性，每个消息都不同，即便是承载者相同消息体的消息。");
                //回复消息的目的地。带有这样属性的消息通常是发送方希望有一个响应，这个响应是可选的。
                textMessage.setJMSReplyTo(responseDestination);
                //包含了消息发往的目的地或者主题信息。
                //textMessage.setJMSDestination(destination);
                //发送时间
                textMessage.setJMSTimestamp(100L);
                //与当前消息关联的其他消息的标示
                textMessage.setJMSCorrelationID("");
                //带有该字段的消息通常过去发送过但是没有被确认，如果要再次发送，提供者必须设置该字段。如果true，则消息接受者必须进行消息重复处理的逻辑。
                textMessage.setJMSRedelivered(true);
                //JMSType：消息类型标示。官方文档的解释：
                //JMSExpiration ：过期时间。
                //JMSPriority：优先级。
                return textMessage;
            }
        });
    }

}  