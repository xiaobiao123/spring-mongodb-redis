package mq.activemq.basic.knowledge.test.PTP;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/3/4.
 */
public class QueueReceiver {

    public static final String BROKER_URL = "tcp://172.30.22.239:61616";

    public static final String TARGET = "queue.send.test";

    public static void run() throws JMSException, InterruptedException {
        QueueConnection connection = null;

        QueueSession session = null;

        QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);

        connection = factory.createQueueConnection();

//        connection.start();
        session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(TARGET);

        javax.jms.QueueReceiver queueReceiver = session.createReceiver(queue);

        queueReceiver.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message != null) {
                    MapMessage mapMessage = (MapMessage) message;
                    try {
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + mapMessage.getString("message"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //给出打印的时间
        Thread.sleep(1000);
        session.commit();
        session.close();
        connection.close();
    }

    public static void main(String[] args) throws JMSException, InterruptedException {
        QueueReceiver.run();
    }
}
