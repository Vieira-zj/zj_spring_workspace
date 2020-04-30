package com.zhengjin.springmvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springmvc4.service.DemoService;

@RestController
public class MyRestController {

	@Autowired
	DemoService demoService;

	@RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String testRest() {
		return demoService.saySomething();
	}

	// test: curl -XPOST "http://localhost:8080/springmvc4/testPost" -H
	// "Content-Type:text/plain" -d "from curl post"
	@RequestMapping(value = "/testPost", method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain;charset=UTF-8")
	public String testPost(@RequestBody String message) {
		return "POST data: " + message;
	}

}
