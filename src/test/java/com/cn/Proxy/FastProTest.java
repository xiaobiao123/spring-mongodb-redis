package com.cn.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import cn.springmvc.service.impl.UserServiceImpl;

public class FastProTest   {

	@Test
	public void test() throws InvocationTargetException {
		Object[] ssstring={new User()};
		UserServiceImpl use=new UserServiceImpl();
		Method[] methods=use.getClass().getMethods();
		
		methods[0].getParameterTypes();
		
		
		FastClass serviceFastClass = FastClass.create(UserService.class);
		FastMethod serviceFastMethod = serviceFastClass.getMethod("insertUser",methods[0].getParameterTypes());
		serviceFastMethod.invoke(serviceFastClass,ssstring);
	}
	
	
	@Test
	public void testSS() throws InvocationTargetException {
//		User.class.getName();
		String[] ss=new String[] {User.class.getName()};
		System.out.println();
//		new Object[] { requestHeader }
	}

}
