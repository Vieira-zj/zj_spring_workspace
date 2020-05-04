package com.zhengjin.springboot_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhengjin.springboot_jpa.dao.PersonRepository;
import com.zhengjin.springboot_jpa.domain.Person;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public Person savePersonWithRollBack(Person person) {
		Person p = personRepository.save(person);
		if (p.getName().equals("test_tx")) {
			throw new IllegalArgumentException("test_tx is exist, and data will be rollback");
		}
		return p;
	}

	@Override
	@Transactional(noRollbackFor = IllegalArgumentException.class)
	public Person savePersonWithoutRollBack(Person person) {
		Person p = personRepository.save(person);
		if (p.getName().equals("test_tx")) {
			throw new IllegalArgumentException("test_tx is exist, and data will NOT be rollback");
		}
		return p;
	}

}
