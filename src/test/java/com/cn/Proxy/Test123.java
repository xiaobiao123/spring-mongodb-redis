package com.cn.Proxy;

import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import cn.springmvc.service.impl.UserServiceImpl;

public class Test123 {

	private CopyOnWriteArrayList<User> connectedHandlers = new CopyOnWriteArrayList<>();
	@Test
	public void test() {
		UserServiceImpl use=new UserServiceImpl();
		Method[] methods=use.getClass().getMethods();
		
		methods[0].getParameterTypes();
		
		FastClass serviceFastClass = FastClass.create(UserService.class);
		FastMethod serviceFastMethod = serviceFastClass.getMethod("insertUser",methods[0].getParameterTypes());
		//serviceFastMethod.invoke(serviceFastClass,new Object[]);
	}
	@Test
	public void testnew() {
		
		CopyOnWriteArrayList<User> handlers = (CopyOnWriteArrayList<User>) this.connectedHandlers.clone();
	}
	
	

}
