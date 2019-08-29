package com.cn.mq.activemq.basic.knowledge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/** 
 * 持久订阅设置唯一的客户端ID和订阅者ID。 
 */  
public class ConsumerPersistent {  
  
    public static void main(String[] args) throws JMSException {
        String clientId = "client_id";
          
        // 连接到ActiveMQ服务器  
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.30.22.9:61616");
        Connection connection = factory.createConnection();  
        //客户端ID,持久订阅需要设置  
        connection.setClientID(clientId);  
        connection.start();  
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 创建主题  
        Topic topic = session.createTopic("slimsmart.topic.test");
        // 创建持久订阅,指定客户端ID。
        MessageConsumer consumer = session.createDurableSubscriber(topic,clientId);
        consumer.setMessageListener(new MessageListener() {
            // 订阅接收方法
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println("Received message: " + tm.getText()+":"+tm.getStringProperty("property"));
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
  
    }  
}  