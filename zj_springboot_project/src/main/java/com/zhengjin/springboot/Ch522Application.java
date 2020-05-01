package com.zhengjin.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengjin.spring_boot_starter_hello.HelloService;
import com.zhengjin.springboot.ch6_2_3.config.AuthorSettings;
import com.zhengjin.springboot.ch7_2_4.bean.Person;

@Controller
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
	public @ResponseBody String index() {
		String ret = "Hello Spring Boot";
		ret += "<br>" + String.format("book author:%s, name:%s", bookAuthor, bookName);
		ret += "<br>" + String.format("author name:%s, age:%d", authorSettings.getName(), authorSettings.getAge());
		ret += "<br>" + "Customized auto config starter: " + helloService.sayHello();
		return ret;
	}

	// path: /helloboot/thymeleaf
	@RequestMapping("/thymeleaf")
	public String people(Model model) {
		Person single = new Person("aa", 11);

		List<Person> people = new ArrayList<Person>();
		Person p1 = new Person("xx", 11);
		Person p2 = new Person("yy", 22);
		Person p3 = new Person("zz", 33);
		people.add(p1);
		people.add(p2);
		people.add(p3);

		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		return "thymeleaf";
	}

	public static void main(String[] args) {

		SpringApplication.run(Ch522Application.class, args);
	}

}
