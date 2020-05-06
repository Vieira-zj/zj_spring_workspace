package com.zhengjin.springboot_jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot_jpa.domain.Person;
import com.zhengjin.springboot_jpa.service.DemoService;

@RestController
@RequestMapping("/trx")
public class TransactionController {

	@Autowired
	DemoService demoService;

	// curl -v
	// "http://localhost:8081/trx/rollback?name=test_tx&age=32&address=shengzhen"
	@RequestMapping("/rollback")
	public Person rollback(Person person) {
		return demoService.savePersonWithRollBack(person);
	}

	// curl -v
	// "http://localhost:8081/trx/norollback?name=test_tx&age=32&address=shengzhen"
	@RequestMapping("/norollback")
	public Person noRollback(Person person) {
		return demoService.savePersonWithoutRollBack(person);
	}

}
