package com.tiantian.springintejms.service.impl;

import javax.annotation.Resource;
import javax.jms.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;  
import org.springframework.stereotype.Component;  
   
import com.tiantian.springintejms.service.ProducerService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                textMessage.setJMSReplyTo(responseDestination);
                return textMessage;
            }
        });
    }

}  