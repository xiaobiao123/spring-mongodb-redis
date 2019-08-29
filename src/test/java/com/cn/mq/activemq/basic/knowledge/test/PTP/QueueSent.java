package com.cn.mq.activemq.basic.knowledge.test.PTP;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/3/4.
 */
public class QueueSent {
    public static final int SEND_NUM = 100;

    public static final String BROKER_URL = "tcp://172.30.22.239:61616";

    public static final String DESTINATION = "queue.send.test";

    public static void run() throws JMSException {

        QueueConnection connection = null;
        QueueSession session = null;
        QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
        connection = factory.createQueueConnection();
//      connection.start

        // 创建Session，参数解释：
        // 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，
        // 消息发送程序负责处理这个错误。
        // 第二个参数消息的确认模式：
        // AUTO_ACKNOWLEDGE ： 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
        // CLIENT_ACKNOWLEDGE ： 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
        // DUPS_OK_ACKNOWLEDGE ： 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。

        session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(DESTINATION);
        QueueSender sender = session.createSender(queue);
        sender.setDeliveryMode(DeliveryMode.PERSISTENT);//非持久化
        sendMessage(sender, session);
        session.commit();
        session.close();
        connection.close();
    }

    private static void sendMessage(QueueSender sender, QueueSession session) throws JMSException {
        for (int i=0;i<100;i++){
            String message = "xxxxxxxxxxx"+i;
            MapMessage map = session.createMapMessage();
            map.setString("message", message);
            map.setLong("age", 1231312);
            sender.send(map);
        }

    }


    public static void main(String[] args) throws JMSException {
        QueueSent.run();
    }
}
