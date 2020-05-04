package com.zhengjin.springboot_jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_jpa.domain.Person;
import com.zhengjin.springboot_jpa.service.CacheService;

@RestController
public class CacheController {

	@Autowired
	CacheService cacheService;

	// curl -v "http://localhost:8081/put?name=dd&age=32&address=chengdu"
	@RequestMapping("/put")
	public Person put(Person person) {
		return cacheService.save(person);
	}

	// curl -v "http://localhost:8081/get?id=1"
	@RequestMapping("/get")
	public Person get(Person person) {
		return cacheService.findOne(person);
	}

	// curl -v "http://localhost:8081/evit?id=1"
	@RequestMapping("/evit")
	public String evit(Long id) {
		cacheService.remove(id);
		return "ok";
	}

}
