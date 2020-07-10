package com.zhengjin.spring4.ch3.taskscheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过EnableScheduling和Scheduled注释来执行定时任务。
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
		TimeUnit.SECONDS.sleep(13L);
		System.out.println("context close");
		context.close();
	}

}
