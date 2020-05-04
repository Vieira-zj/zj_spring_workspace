package com.zhengjin.springboot_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhengjin.springboot_jpa.dao.PersonRepository;
import com.zhengjin.springboot_jpa.domain.Person;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	PersonRepository personRepository;

	// @CachePut 总是更新到缓存
	@Override
	@CachePut(value = "people", key = "#person.id")
	public Person save(Person person) {
		Person p = this.personRepository.save(person);
		System.out.println("cache record with id: " + person.getId());
		return p;
	}

	// @CacheEvict 从缓存中删除
	@Override
	@CacheEvict(value = "people")
	public void remove(Long id) {
		System.out.println("remove cached record with id: " + id);
		this.personRepository.delete(id);
	}

	// @Cacheable 有，则返回缓存数据；没有，则添加到缓存
	@Override
	@Cacheable(value = "people", key = "#person.id")
	public Person findOne(Person person) {
		Person p = this.personRepository.findOne(person.getId());
		System.out.println("cache new record with id: " + person.getId());
		return p;
	}

}
