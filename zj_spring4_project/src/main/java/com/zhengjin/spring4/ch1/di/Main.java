package com.zhengjin.spring4.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
		System.out.println(useFunctionService.SayHello("Spring"));

		FunctionService2 service2 = context.getBean(FunctionService2Impl.class);
		System.out.println(service2.sayHello("Spring Impl"));

		context.close();
	}

}
