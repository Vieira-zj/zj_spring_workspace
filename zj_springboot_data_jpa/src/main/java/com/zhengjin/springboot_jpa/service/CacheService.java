package com.zhengjin.springboot_jpa.service;

import com.zhengjin.springboot_jpa.domain.Person;

public interface CacheService {

	public Person save(Person person);

	public void remove(Long id);

	public Person findOne(Person person);

}
