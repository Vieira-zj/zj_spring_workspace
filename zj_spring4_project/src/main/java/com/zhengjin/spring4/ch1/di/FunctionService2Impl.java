package com.zhengjin.spring4.ch1.di;

import org.springframework.stereotype.Service;

@Service
public class FunctionService2Impl implements FunctionService2 {

	@Override
	public String sayHello(String word) {
		return "Hello " + word + "!";
	}

}
