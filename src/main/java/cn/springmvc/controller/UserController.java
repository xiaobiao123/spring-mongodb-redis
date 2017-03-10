package cn.springmvc.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
	@RequestMapping("index")
	public String index() {
		return "index";

	}

}
