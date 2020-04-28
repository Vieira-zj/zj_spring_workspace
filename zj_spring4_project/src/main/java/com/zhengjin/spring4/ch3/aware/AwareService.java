package com.zhengjin.spring4.ch3.aware;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

	private String beanName;
	private ResourceLoader loader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.loader = resourceLoader;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void outputResult() {
		System.out.println("Bean name: " + this.beanName);
		Resource resource = this.loader.getResource("classpath:com/zhengjin/spring4/ch3/aware/test.txt");
		try {
			System.out.println(
					"ResourceLoader load file content: " + IOUtils.toString(resource.getInputStream(), "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
