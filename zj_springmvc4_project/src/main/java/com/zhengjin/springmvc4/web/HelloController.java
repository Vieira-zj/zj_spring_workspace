package com.zhengjin.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/index")
	public String hello() {
		// 通过ViewResolver的Bean配置: 映射 WEB-INF/classes/views/index.jsp
		return "index";
	}

}
