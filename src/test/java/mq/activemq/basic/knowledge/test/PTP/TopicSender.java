package mq.activemq.basic.knowledge.test.PTP;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by gwb on 2017/3/6.
 */
public class TopicSender {

    public static final int SEND_NUM = 1000;

    public static final String BROKER_URL = "tcp://172.30.22.239:61616";

    public static final String DESTIONTION = "hoo.mq.topic.persistent";

    public static void run() throws JMSException {
        TopicConnection connection = null;
        TopicSession session = null;

        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, BROKER_URL);

        connection = factory.createTopicConnection();
        connection.setClientID("my_topic");
//        connection.start(); //可以少

        session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(DESTIONTION);

        TopicPublisher publisher = session.createPublisher(topic);
        // 设置持久化模式
        publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
        sendMessage(publisher, session);
        session.commit();
        session.close();
        connection.close();

    }

    private static void sendMessage(TopicPublisher publisher, TopicSession session) throws JMSException {
        String msm = "this is a topic type message";
        MapMessage message = session.createMapMessage();
        for (int i = 0; i < SEND_NUM; i++) {
            message.setString("message", msm + "====" + i);
            message.setLong("long", i);
            publisher.send(message);
        }
    }

    public static void main(String[] args) throws JMSException {
        TopicSender.run();
    }
}

