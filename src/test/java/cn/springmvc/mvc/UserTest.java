package cn.springmvc.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Maps;
import mongodb.example.MongodbTest;
import mongodb.example.data.model.UserEntity;
import org.junit.Before;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import cn.springmvc.model.User;

import cn.springmvc.service.UserService;
import org.springframework.stereotype.Service;

public class UserTest {

    private UserService userService;

    @Before
    public void before() {

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:conf/spring.xml",
                        "classpath:conf/spring-mybatis.xml"});
//		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:conf/spring.xml" });
        userService = (UserService) context.getBean("userServiceImpl");

        // //创建IoC容器管理的Bean的xml配置文件，即定位资源
        // ClassPathResource resource = new ClassPathResource("spring.xml");
        // //创建BeanFactory
        // DefaultListableBeanFactory F1factory = new DefaultListableBeanFactory
        // ();
        // //创键Bean定义读取器
        // XmlBeanDefinitionReader reader = new
        // XmlBeanDefinitionReader(F1factory);
        // //使用Bean定义读取器读入Bean配置信息，即载入配置
        // reader.loadBeanDefinitions(resource);

        ////定位资源
        //ClassPathResource resource=new ClassPathResource("spring.xml");
        ////创建ioc容器管理
        //DefaultListableBeanFactory F1factory=new DefaultListableBeanFactory();
        ////创建资源读取器
        //XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(F1factory);
        ////读取器读入bean的配置信息
        //reader.loadBeanDefinitions(resource);

    }

    @Test
    public void addUser() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setNickname("nickname" + 1);
            user.setState(1);
            executorService.execute(new InserUser(user));
            Thread.sleep(1000);
        }
        System.out.println();

        executorService.shutdown();//关闭线程池
        //判断是否所有的线程已经运行完
        while (!executorService.isTerminated()) {

        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("所有子线程已执行完毕");

    }

    public class InserUser implements Runnable {
        private User user;

        public InserUser(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            synchronized (user) {
                userService.insertUser(user);
            }

        }
    }


}
