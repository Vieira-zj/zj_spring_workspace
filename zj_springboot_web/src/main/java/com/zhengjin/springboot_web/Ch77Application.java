package com.zhengjin.springboot_web;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_web.domain.Person;

@RestController
@SpringBootApplication
public class Ch77Application {

	@RequestMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Person search(String personName) {
		return new Person(personName, new Random().nextInt(40), "ShangHai");
	}

	// url: http://localhost:8081/action.html
	public static void main(String[] args) {

		SpringApplication.run(Ch77Application.class, args);
	}
}
