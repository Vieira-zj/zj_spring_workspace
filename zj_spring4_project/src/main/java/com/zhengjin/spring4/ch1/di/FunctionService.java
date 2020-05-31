package com.zhengjin.spring4.ch1.di;

import org.springframework.stereotype.Component;

@Component
public class FunctionService {

	public String sayHello(String word) {
		return "Hello " + word + "!";
	}

}
