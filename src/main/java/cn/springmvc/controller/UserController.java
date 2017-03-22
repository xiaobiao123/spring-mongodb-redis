package cn.springmvc.controller;

import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
	private Logger logger =Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("addUser")
	@ResponseBody
	public void  addUser(){
		User user=new User();
		user.setState(1);
		user.setNickname(System.currentTimeMillis()+"");
		userService.insertUser(user);
	}


	@RequestMapping("index")
	public String index() {
		logger.info("loadRunner"+"========================");
		return "index";

	}
	@RequestMapping("index2")
	public String index2() {
		logger.info("loadRunner"+"========================");
		return "index";

	}

}
