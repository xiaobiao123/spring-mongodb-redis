package mq.kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;
  
public class KafkaProducerTest {  
    public static void main(String[] args) {  
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{  
              "classpath*:conf/kafka-consumer.xml", "classpath*:conf/kafka_producer.xml"
      });
      context.start();
      while (true) {  
        try {  
          Thread.sleep(Long.MAX_VALUE);  
        } catch (InterruptedException e) {  
          e.printStackTrace();  
        }  
      }  
    }  
}  