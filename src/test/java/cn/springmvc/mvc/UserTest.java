package cn.springmvc.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import cn.springmvc.model.User;

import cn.springmvc.service.UserService;

public class UserTest {

	private UserService userService;

	@Before
	public void before() {

		@SuppressWarnings("resource")
		 ApplicationContext context = new ClassPathXmlApplicationContext(
		 new String[] { "classpath:conf/spring.xml",
		 "classpath:conf/spring-mybatis.xml" });
//		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:conf/spring.xml" });
		userService = (UserService) context.getBean("userServiceImpl");

		// //创建IoC容器管理的Bean的xml配置文件，即定位资源
		// ClassPathResource resource = new ClassPathResource("spring.xml");
		// //创建BeanFactory
		// DefaultListableBeanFactory factory = new DefaultListableBeanFactory
		// ();
		// //创键Bean定义读取器
		// XmlBeanDefinitionReader reader = new
		// XmlBeanDefinitionReader(factory);
		// //使用Bean定义读取器读入Bean配置信息，即载入配置
		// reader.loadBeanDefinitions(resource);

	}

	@Test
	public void addUser() {
		 User user = new User();
		 user.setNickname("你好");
		 user.setState(2);
		 
		 
		 
//		 
//		Date dNow = new Date(); // 当前时间
//		Date nTime = new Date();
//		Calendar calendar = Calendar.getInstance(); // 得到日历
//		calendar.setTime(dNow);// 把当前时间赋给日历
//		calendar.add(Calendar.MONTH, +3); // 设置过期时间，dto.getPastType()==null时查询所有
//		nTime = calendar.getTime(); // 得到后3月的时间
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormat.format(nTime));
		 System.out.println(userService.insertUser(user));

	}

}
