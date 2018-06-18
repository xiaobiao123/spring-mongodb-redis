

ActiveMQ

<http://elim.iteye.com/blog/1893676>

**消息类型**：queue/topic
**spring提供的ConnectionFactory类型**SingleConnectionFactory/CachingConnectionFactory
**ActiveMQ提供的**PooledConnectionFactory
**消息监听及消息监听容器：**
配置监听容器MessageListenerContainer必须有三个参数ConnectionFactoryMessageListener,Destination
spring提供的有两种监听容器SimpleMessageListenerContainer、DefaultMessageListenerContainer
消息监听MessageListener、SessionAwareMessageListener和MessageListenerAdapter（主要作用是将接收到的消息进行类型转换）

**消息转换器MessageConverter：**spring默认实现的消息转换器SimpleMessageConverter

**事物管理：**只需要在定义对应的消息监听容器时指定其sessionTransacted属性为true(仅支持消息，不支持消息与数据库混合使用)

使用jtaTransactionManager配置事物

**activemq持久化的方式有三种 文件，MYSql，Oracle** <http://www.cnblogs.com/tommyli/archive/2010/09/13/1825205.html>

消息签收：

```
Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。异常也会确认消息，应该是在执行之前确认的
Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。可以在失败的
时候不确认消息,不确认的话不会移出队列，一直存在，下次启动继续接受。接收消息的连接不断开，其他的消费者也不会接受（正常情况下队列模式不存在其他消费者）
DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
```

# [Activemq管理和基本介绍](http://www.cnblogs.com/raphael5200/p/5787000.html)

1、ActiveMQ服务器工作模型：通过ActiveMQ消息服务交换消息

2、ActiveMQ消息传送模型：PTP（即点对点模型）和Pub/Sub（即发布 /订阅模型）

3、ActiveMQ消息选择器：

4、ActiveMQ消息签收:

```
在不带事务的 Session 中，一条消息何时和如何被签收取决于Session的设置。 
1．Session.AUTO_ACKNOWLEDGE 
当客户端从 receive 或 onMessage成功返回时，Session 自动签收客户端的这条消息的收条。在AUTO_ACKNOWLEDGE的 Session 中，同步接收 receive是上述三个阶段的一个例外，在这种情况下，收条和签收紧随在处理消息之后发生。 
2．Session.CLIENT_ACKNOWLEDGE 
    客户端通过调用消息的 acknowledge 方法签收消息。在这种情况下，签收发生在 Session 层面：签收一个已消费的消息会自动地签收这个 Session 所有已消费消息的收条。 
3．Session.DUPS_OK_ACKNOWLEDGE 
   此选项指示 Session 不必确保对传送消息的签收。它可能引起消息的重复，但是降低了 Session 的开销，所以只有客户端能容忍重复的消息，才可使用（如果ActiveMQ 再次传送同一消息，那么消息头中的JMSRedelivered 将被设置为 true）。客户端成功接收一条消息的标志是这条消息被签收。成功接收一条消息一般包括如下三个阶段： 
1．客户端接收消息； 
2．客户端处理消息； 
3．消息被签收。签收可以由 ActiveMQ发起，也可以由客户端发起，取决于 Session 签收模式的设置。 在带事务的 Session 中，签收自动发生在事务提交时。如果事务回滚，所有已经接收的消息将会被再次传送。 
```

5、ActiveMQ消息传送模式:PERSISTENT 和 NON_PERSISTENT 两种。 

6、ActiveMQ优先级设置

7、ActiveMQ消息过期设置

8、ActiveMQ持久订阅设置:

```
1． 为连接设置一个客户 ID； 
2． 为订阅的主题指定一个订阅名称； 
上述组合必须唯一。 
```

9、ActiveMQ异步发送消息:

10、ActiveMQ消费者特性:（1）消费者异步分派 （2）消费者优先级（3）独占消费者

11、ActiveMQ消息预取机制



### 消息发送者配置

```
<!--cdc文件系统消息队列 -->
<bean id="cdcWareskuQueue" class="org.apache.activemq.command.ActiveMQQueue">
    <constructor-arg value="cdc-waresku-queue"/>
</bean>
 <!-- Spring提供的JMS工具类，它可以进行消息发送、接收等 -->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <!-- 这个connectionFactory对应的是我们定义的Spring提供的那个ConnectionFactory对象 -->
        <property name="connectionFactory" ref="jmsConnectionFactory"/>
    </bean>
<bean id="jmsConnectionFactory"
      class="org.apache.activemq.ActiveMQConnectionFactory">
    <!--connection 相关属性-->
    <property name="brokerURL" value="${common.mq.brokersURL}"/>
    <!--<property name="userName" value="admin"/>-->
    <!--<property name="password" value="password"/>-->
    <!--因spring jms prefetch导致带宽过高问题，特关闭prefetch功能-->
    <property name="prefetchPolicy">
        <bean class="org.apache.activemq.ActiveMQPrefetchPolicy">
            <property name="queuePrefetch" value="1"/>
            <property name="topicPrefetch" value="1"/>
        </bean>
    </property>
</bean>
<bean id="pooledConnectionFactory" class="org.apache.activemq.pool.PooledConnectionFactory" init-method="start"
      destroy-method="stop">
    <property name="connectionFactory" ref="jmsConnectionFactory"/>
    <!--pool相关参数-->
    <property name="maxConnections" value="${common.mq.maxConnections}"/>
    <property name="maximumActiveSessionPerConnection" value="${common.mq.maximumActiveSessionPerConnection}"/>
</bean>
```

在spring中使用

```
/**
 * 商品启用禁用消息使用
 */
@Service
public class MQManager {

    private static final Logger logger = LoggerFactory.getLogger(MQManager.class);

    @Autowired
    private Destination cdcWareskuQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendWareSkuMsg(final List<WareSku> list) {
        try {
            jmsTemplate.send(cdcWareskuQueue, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(JSON.toJSONString(list));
                    textMessage.setJMSReplyTo(cdcWareskuQueue);
                    return textMessage;
                }
            });
        } catch (Exception e) {
            logger.error("商品sku信息发送消息失败！");
            e.printStackTrace();
        }
    }
}
```
### 消费者配置

```
<!--cdc商品规格属性变更-->
<bean id="cdcWareskuQueue" class="org.apache.activemq.command.ActiveMQQueue">
    <constructor-arg value="cdc-waresku-queue"/>
</bean>

<!-- Spring提供的JMS工具类，它可以进行消息发送、接收等 -->
<bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
    <!-- 这个connectionFactory对应的是我们定义的Spring提供的那个ConnectionFactory对象 -->
    <property name="connectionFactory" ref="jmsConnectionFactory"/>
</bean>

<bean id="jmsConnectionFactory"
      class="org.apache.activemq.ActiveMQConnectionFactory">
    <!--connection 相关属性-->
    <property name="brokerURL" value="${common.mq.brokersURL}"/>
    <!--<property name="userName" value="admin"/>-->
    <!--<property name="password" value="password"/>-->
    <!--因spring jms prefetch导致带宽过高问题，特关闭prefetch功能-->
    <property name="prefetchPolicy">
        <bean class="org.apache.activemq.ActiveMQPrefetchPolicy">
            <property name="queuePrefetch" value="1"/>
            <property name="topicPrefetch" value="1"/>
        </bean>
    </property>
</bean>
<bean id="pooledConnectionFactory" class="org.apache.activemq.pool.PooledConnectionFactory" init-method="start"
      destroy-method="stop">
    <property name="connectionFactory" ref="jmsConnectionFactory"/>
    <!--pool相关参数-->
    <property name="maxConnections" value="${common.mq.maxConnections}"/>
    <property name="maximumActiveSessionPerConnection" value="${common.mq.maximumActiveSessionPerConnection}"/>
</bean>

<!--真实环境用message consumer-->
<bean id="jmsListenerContainer" class="org.springframework.jms.listener.DefaultMessageListenerContainer">
    <property name="connectionFactory" ref="jmsConnectionFactory"/>
    <property name="destination" ref="cdcWareskuQueue"/>
    <!--concurrentConsumers，maxConcurrentConsumers
      初始化默认值：
        concurrentConsumers = 1;
        maxConcurrentConsumers = 1;
SimpleMessageListenerContainer允许创建多个Session和MessageConsumer来接收消息。具体的个数由 concurrentConsumers属性指定。需要注意的是，应该只是在Destination为Queue的时候才使用多个 MessageConsumer（Queue中的一个消息只能被一个Consumer接收），虽然使用多个MessageConsumer会提高消息处理 的性能，但是消息处理的顺序却得不到保证：消息被接收的顺序仍然是消息发送时的顺序，但是由于消息可能会被并发处理，因此消息处理的顺序可能和消息发送的 顺序不同。此外，不应该在Destination为Topic的时候使用多个MessageConsumer，这是因为多个 MessageConsumer会接收到同样的消息。
顶-->
    <property name="messageListener" ref="wareSqkuMessageListener"/>
    <property name="sessionAcknowledgeMode" value="2"/>
    <property name="errorHandler" ref="messageErrorHandler"/>
    <property name="concurrentConsumers" value="2"/>
    <!--<property name="concurrentConsumers" value="${oms.concurrentConsumers}"/>-->
</bean>
```



### 消费者使用

```
 */
@Component("wareSqkuMessageListener")
@Log4j
public class MQMessageListenerManager implements SessionAwareMessageListener<Message> {
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();
        List<WareSkuVo> list = JSON.parseObject(text, List.class);

        for (WareSkuVo wareSkuVo : list) {
            log.info(wareSkuVo.getSkuCode());
        }
    }
}
```