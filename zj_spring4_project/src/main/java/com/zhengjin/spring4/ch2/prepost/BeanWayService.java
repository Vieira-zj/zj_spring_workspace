package com.zhengjin.spring4.ch2.prepost;

public class BeanWayService {

	public void init() {
		System.out.println("@Bean-init-method");
	}

	public BeanWayService() {
		super();
		System.out.println("Constructor-BeanWayService");
	}

	public void destroy() {
		System.out.println("@Bean-destroy-method");
	}

}
