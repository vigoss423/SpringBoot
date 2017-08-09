package com.blueocean.web.hellomanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("helloIndex")
public class HelloTestController{
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("首页。。。");
		return "index";
	}
	
}
