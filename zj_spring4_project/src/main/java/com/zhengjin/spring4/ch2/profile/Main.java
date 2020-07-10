package com.zhengjin.spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Set diff bean of Configuration by profile.
 *
 */
public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev");
		context.register(ProfileConfig.class);
		context.refresh();

		DemoBean demoBean = context.getBean(DemoBean.class);
		System.out.println(demoBean.getContent());

		context.close();
	}

}
