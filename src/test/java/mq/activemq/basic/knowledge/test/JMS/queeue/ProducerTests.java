package mq.activemq.basic.knowledge.test.JMS.queeue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by gwb on 2017/9/12.
 */
public class ProducerTests {
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
            //第一个参数是是否是事务型消息，设置为true,第二个参数无效
            //第二个参数是
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。异常也会确认消息，应该是在执行之前确认的
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。可以在失败的
            //时候不确认消息,不确认的话不会移出队列，一直存在，下次启动继续接受。接收消息的连接不断开，其他的消费者也不会接受（正常情况下队列模式不存在其他消费者）
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
            //待测试
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("test-queue");

            producer = session.createProducer(destination);
            //对象有效期
            producer.setTimeToLive(10000L);
            //优先级
            producer.setPriority(1);
            //是否持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MqBean bean = new MqBean();
            bean.setAge(13);
            for(int i=0;i<10;i++){
                bean.setName("小黄"+i);
                producer.send(session.createObjectMessage(bean));

            }
            producer.close();
            System.out.println("呵呵");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
