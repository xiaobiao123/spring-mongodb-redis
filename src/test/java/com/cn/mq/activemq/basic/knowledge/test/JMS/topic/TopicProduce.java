package com.cn.mq.activemq.basic.knowledge.test.JMS.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import com.cn.mq.activemq.basic.knowledge.test.JMS.queeue.*;

import javax.jms.*;

/**
 * Created by gwb on 2017/9/12.
 */
public class TopicProduce {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://172.30.22.9:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            destination = session.createTopic("test-topic");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //优先级不能影响先进先出。。。那这个用处究竟是什么呢呢呢呢
            MqBean bean = new MqBean();
            bean.setAge(13);
            for (int i = 0; i < 10; i++) {
                Thread.sleep(10);
                bean.setName("小黄" + i);
                producer.send(session.createObjectMessage(bean));
            }
            System.out.println("呵呵");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
