package com.zhengjin.spring4.ch3.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
		ListService listService = context.getBean(ListService.class);
		System.out.println(String.format("'%s' system list file command: %s",
				context.getEnvironment().getProperty("os.name"), listService.showListCmd()));

		context.close();
	}

}
