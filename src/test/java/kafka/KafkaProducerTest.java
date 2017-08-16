package kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;
  
public class KafkaProducerTest {  
    public static void main(String[] args) {  
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{  
              "classpath:spring/spring-kafka-consumer.xml", "classpath:spring/spring-kafka-provider.xml"  
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