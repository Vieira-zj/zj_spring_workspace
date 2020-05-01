package com.zhengjin.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.spring_boot_starter_hello.HelloService;
import com.zhengjin.springboot.ch6_2_3.config.AuthorSettings;

@RestController
@SpringBootApplication
public class Ch522Application {

	@Value("${book.author}")
	private String bookAuthor;

	@Value("${book.name}")
	private String bookName;

	@Autowired
	AuthorSettings authorSettings;

	@Autowired
	HelloService helloService;

	@RequestMapping("/")
	String index() {
		String ret = "Hello Spring Boot";
		ret += "<br>" + String.format("book author:%s, name:%s", bookAuthor, bookName);
		ret += "<br>" + String.format("author name:%s, age:%d", authorSettings.getName(), authorSettings.getAge());
		ret += "<br>" + "Customized auto starter config: " + helloService.sayHello();
		return ret;
	}

	public static void main(String[] args) {

		SpringApplication.run(Ch522Application.class, args);
	}

}
