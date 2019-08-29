package cn.springmvc.mvc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.springmvc.model.User;

import cn.springmvc.service.UserService;

public class UserTest {

    private UserService userService;

    @Before
    public void before() {

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:conf/spring.xml",
                        "classpath:conf/spring-mybatis.xml"});
		//ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:conf/com.cn.spring.xml" });
        userService = (UserService) context.getBean("userServiceImpl");
        System.out.println(  context.getBean("appleFactoryBean"));
        //System.out.println(  context.getBean("&appleFactoryBean"));



        ////定位资源
        //ClassPathResource resource=new ClassPathResource("classpath:conf/com.cn.spring.xml");
        ////创建ioc容器管理
        //DefaultListableBeanFactory F6factory=new DefaultListableBeanFactory();
        ////创建资源读取器
        //XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(F6factory);
        ////读取器读入bean的配置信息
        //reader.loadBeanDefinitions(resource);

    }

    @Test
    public void addUser() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Long start=System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setNickname("nickname" + 1);
            user.setState(1);
           // executorService.execute(new InserUser(user));
            Thread.sleep(1000);
        }
        System.out.println();


        //executorService.shutdown();//关闭线程池
        ////判断是否所有的线程已经运行完
        //while (!executorService.isTerminated()) {
        //
        //}
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis()-start);
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
