package com.zhengjin.springboot_jpa.service;

import com.zhengjin.springboot_jpa.domain.Person;

public interface DemoService {

	public Person savePersonWithRollBack(Person person);

	public Person savePersonWithoutRollBack(Person person);

}
