package com.zhengjin.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/index")
	public String hello() {
		// index.jsp页面
		return "index";
	}

}
