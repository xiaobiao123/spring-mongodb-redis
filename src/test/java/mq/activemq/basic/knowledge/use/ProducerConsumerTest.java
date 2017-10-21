package mq.activemq.basic.knowledge.use;

import com.tiantian.springintejms.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * http://elim.iteye.com/blog/1893676
 *
 * <h1>消息类型  queue/topic
 * <p1>spring提供的ConnectionFactory类型有SingleConnectionFactory/CachingConnectionFactory
 * <p2>ActiveMQ提供的PooledConnectionFactory

 * <h2>消息监听及消息监听容器
 * <p1>配置消息监听容器MessageListenerContainer必须有三个参数ConnectionFactory、MessageListener,Destination
 * spring提供的有两种监听容器SimpleMessageListenerContainer、DefaultMessageListenerContainer
 * <p2>消息监听MessageListener、SessionAwareMessageListener和MessageListenerAdapter（主要作用是将接收到的消息进行类型转换）

 * <h3>消息转换器MessageConverter
 * <p>spring默认实现的消息转换器SimpleMessageConverter

 * <h4>事物管理
 * <p1>只需要在定义对应的消息监听容器时指定其sessionTransacted属性为true(仅支持消息，不支持消息与数据库混合使用)
 * <p2>使用jtaTransactionManager配置事物
 *
 *
 *
 * <h5>activemq持久化的方式有三种 文件，MYSql，Oracle
 * 参考地址：http://www.cnblogs.com/tommyli/archive/2010/09/13/1825205.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activemq.xml")
public class ProducerConsumerTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    //@Qualifier("queueDestination")
    //@Qualifier("sessionAwareQueue")
    @Qualifier("adapterQueue")
    private Destination destination;


    @Test
    public void testSend() {
        for (int i = 0; i < 2; i++) {
            producerService.sendMessage(destination, "测试MessageListenerAdapter：" + (i + 1));
        }
    }

}
