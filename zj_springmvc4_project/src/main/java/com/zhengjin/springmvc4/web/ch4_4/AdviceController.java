package com.zhengjin.springmvc4.web.ch4_4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengjin.springmvc4.domain.DemoObj;

/**
 * advice全局异常处理。（ControllerAdvice注解）
 *
 */
@Controller
public class AdviceController {

	// path: /advice?id=1&name=xx
	@RequestMapping("/advice")
	public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
		System.out.println("input object: " + obj);
		throw new IllegalArgumentException("参数有误，来自@ModelAttribute: " + msg);
	}

}
