package com.zhengjin.spring4.ch3.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
	}

}
