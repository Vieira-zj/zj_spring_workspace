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
@PropertySource("classpath:com/zhengjin/spring4/ch2/el/test.properties")
public class ElConfig {

	@Value("I love spring!")
	private String normal;

	@Value("#{systemProperties['os.name']}")
	private String osName;

	@Value("#{ T(java.lang.Math).random() * 100.0 }")
	private double randomNumber;

	@Value("#{demoService.another}")
	private String fromAnother;

	@Value("classpath:com/zhengjin/spring4/ch2/el/test.txt")
	private Resource testFile;

	@Value("${book.name}")
	private String bookName;

	// 获得注入的properties
	@Autowired
	private Environment environment;

	// => @Value("${book.name}")
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void outputResource() {
		System.out.println(normal);
		System.out.println(osName);
		System.out.println(randomNumber);
		System.out.println(fromAnother);
		System.out.println(bookName);
		System.out.println(environment.getProperty("book.author"));

		try {
			System.out.println(IOUtils.toString(testFile.getInputStream(), "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
