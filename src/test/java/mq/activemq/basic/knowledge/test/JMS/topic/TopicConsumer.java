package mq.activemq.basic.knowledge.test.JMS.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import mq.activemq.basic.knowledge.test.JMS.queeue.*;
import javax.jms.*;

/**
 * Created by gwb on 2017/9/12.
 *
 * 消息订阅者服务必须先启动，如果消息订阅者在其订阅的消息发送前关闭服务，则不能接受已发送的消息
 */
public class TopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://172.30.22.9:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            //这个最好还是有事务
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createTopic("test-topic");
            consumer = session.createConsumer(destination);

            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MqBean bean = (MqBean) ((ObjectMessage) message).getObject();
                        System.out.println(bean);
                        //里显式调用message.acknowledge()来通知服务器
                        message.acknowledge();
                        if (null != message) {
                            System.out.println("收到消息" + bean.getName());
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
