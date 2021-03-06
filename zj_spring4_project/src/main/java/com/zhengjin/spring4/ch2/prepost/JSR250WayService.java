package com.zhengjin.spring4.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250WayService {

	public JSR250WayService() {
		super();
		System.out.println("Constructor-JSR250WayService");
	}

	@PostConstruct
	public void init() {
		System.out.println("@jsr250-init-method");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("@jsr250-destroy-method");
	}

}
