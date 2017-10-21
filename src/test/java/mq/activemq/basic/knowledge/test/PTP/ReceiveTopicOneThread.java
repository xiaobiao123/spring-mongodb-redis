package mq.activemq.basic.knowledge.test.PTP;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReceiveTopicOneThread {


    /**
     * @param args
     */
    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.30.22.239:61616");
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(true,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("hoo.mq.topic.persistent");
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    MapMessage tm = (MapMessage) message;
                    try {
                        System.out.println("topic consumer is running "+tm.getString("message"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
