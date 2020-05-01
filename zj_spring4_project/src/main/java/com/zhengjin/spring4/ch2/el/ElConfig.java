package com.zhengjin.spring4.ch2.el;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("com.zhengjin.spring4.ch2.el")
@PropertySource("classpath:test.properties")
public class ElConfig {

	@Value("I love spring!")
	private String normal;

	@Value("#{systemProperties['os.name']}")
	private String osName;

	@Value("#{ T(java.lang.Math).random() * 100.0 }")
	private double randomNumber;

	@Value("#{demoService.another}")
	private String fromAnother;

	@Value("classpath:test.txt")
	private Resource testFile;

	@Value("${book.name}")
	private String bookName;

	// => @Value("${book.name}")
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// 获得注入的properties
	@Autowired
	private Environment environment;

	public void outputResource() {
		System.out.println("normal: " + normal);
		System.out.println("os name: " + osName);
		System.out.println("random number: " + randomNumber);
		System.out.println("another bean value: " + fromAnother);
		System.out.println("property book name: " + bookName);
		System.out.println("property book author: " + environment.getProperty("book.author"));

		try {
			System.out.println("file content: " + IOUtils.toString(testFile.getInputStream(), "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
