package com.zhengjin.springboot_data_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_data_crud.model.Person;
import com.zhengjin.springboot_data_crud.repo.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	// curl -v "http://localhost:8081/repo/save?name=cc&address=shanghai&age=25"
	@RequestMapping("/save")
	public Person save(String name, String address, Integer age) {
		Person p = personRepository.save(new Person(null, name, age, address));
		return p;
	}

	// curl -v "http://localhost:8081/repo/q1?address=shanghai"
	@RequestMapping("/q1")
	public List<Person> q1(String address) {
		List<Person> people = personRepository.findByAddress(address);
		return people;
	}

	// curl -v "http://localhost:8081/repo/q2?name=aa&address=wuhan"
	@RequestMapping("/q2")
	public Person q2(String name, String address) {
		Person p = personRepository.findByNameAndAddress(name, address);
		return p;
	}

	// curl -v "http://localhost:8081/repo/q3?name=bb&address=wuhan"
	@RequestMapping("/q3")
	public Person q3(String name, String address) {
		Person p = personRepository.withNameAndAddressQuery(name, address);
		return p;
	}

	// curl -v "http://localhost:8081/repo/sort"
	@RequestMapping("/sort")
	public List<Person> sort() {
		List<Person> people = personRepository.findAll(Sort.by(Sort.Order.asc("age")));
		return people;
	}

	// curl -v "http://localhost:8081/repo/page"
	@RequestMapping("/page")
	public Page<Person> page() {
		Page<Person> pagePeople = personRepository.findAll(PageRequest.of(1, 2));
		return pagePeople;
	}

}
