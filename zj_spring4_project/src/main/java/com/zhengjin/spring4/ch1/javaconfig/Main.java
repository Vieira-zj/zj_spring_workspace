package com.zhengjin.spring4.ch1.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
		System.out.println(useFunctionService.SayHello("java config"));

		FunctionService functionService = context.getBean(FunctionService.class);
		UseFunctionService useFunctionService2 = context.getBean(UseFunctionService.class, functionService);
		System.out.println(useFunctionService2.SayHello("my java config"));
		context.close();
	}

}
