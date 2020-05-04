package com.zhengjin.springboot_jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_jpa.dao.PersonRepository;
import com.zhengjin.springboot_jpa.dao.CustomPersonRepository;
import com.zhengjin.springboot_jpa.domain.Person;

@RestController
public class DataController {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	CustomPersonRepository customRepository;

	// curl -v "http://localhost:8081/save?name=cc&address=shanghai&age=25"
	@RequestMapping("/save")
	public Person save(String name, String address, Integer age) {
		Person p = personRepository.save(new Person(null, name, age, address));
		return p;
	}

	// curl -v "http://localhost:8081/q1?address=shanghai"
	@RequestMapping("/q1")
	public List<Person> q1(String address) {
		List<Person> people = personRepository.findByAddress(address);
		return people;
	}

	// curl -v "http://localhost:8081/q2?name=aa&address=wuhan"
	@RequestMapping("/q2")
	public Person q2(String name, String address) {
		Person p = personRepository.findByNameAndAddress(name, address);
		return p;
	}

	// curl -v "http://localhost:8081/q3?name=bb&address=wuhan"
	@RequestMapping("/q3")
	public Person q3(String name, String address) {
		Person p = personRepository.withNameAndAddressQuery(name, address);
		return p;
	}

	// curl -v "http://localhost:8081/q3?name=bb&address=wuhan"
	@RequestMapping("/q4")
	public Person q4(String name, String address) {
		Person p = personRepository.withNameAndAddressNamedQuery(name, address);
		return p;
	}

	// curl -v "http://localhost:8081/sort"
	@RequestMapping("/sort")
	public List<Person> sort() {
		List<Person> people = personRepository.findAll(new Sort(Direction.ASC, "age"));
		return people;
	}

	// curl -v "http://localhost:8081/page"
	@RequestMapping("/page")
	public Page<Person> page() {
		Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
		return pagePeople;
	}

	// curl -v "http://localhost:8081/wuhan"
	@RequestMapping("/wuhan")
	public List<Person> wuhan() {
		List<Person> people = customRepository.findAllByAddressWuHan();
		return people;
	}

	/**
	 * String类型使用like查询，非String类型使用equal查询。
	 * 
	 * 1. 当person的name有值时，自动对name进行like查询； 2. 当age有值时，进行等于查询； 3.
	 * 当多个值不为空时，自动构造多个查询条件； 4. 当Person所有值为空的时候，默认查询出所有的记录；
	 * 
	 * @param person
	 * @return
	 */
	// curl -v "http://localhost:8081/auto"
	// curl -v "http://localhost:8081/auto?address=han"
	// curl -v "http://localhost:8081/auto?address=han&age=30"
	@RequestMapping("/auto")
	public Page<Person> auto(Person person) {
		Page<Person> pagePeople = customRepository.findByAuto(person, new PageRequest(0, 10));
		return pagePeople;
	}

}
