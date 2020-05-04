package com.zhengjin.springboot_data_redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_data_redis.dao.PersonDao;
import com.zhengjin.springboot_data_redis.domain.Person;

@RestController
public class DataController {

	@Autowired
	PersonDao personDao;

	// curl -v "http://localhost:8081/set"
	@RequestMapping("/set")
	public String set() {
		Person person = new Person("1", "xx", 32);
		this.personDao.setString();
		this.personDao.save(person);
		return "ok";
	}

	// curl -v "http://localhost:8081/getStr"
	@RequestMapping("/getStr")
	public String getStr() {
		return this.personDao.getString();
	}

	// curl -v "http://localhost:8081/getPerson"
	@RequestMapping("/getPerson")
	public Person getPerson() {
		return this.personDao.getPerson();
	}

}
