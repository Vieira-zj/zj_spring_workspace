package com.zhengjin.springboot.ch.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springboot.ch7_2_4.bean.Person;

@RestController
@RequestMapping("/api")
public class ExceptionController {

	// curl http://localhost:8081/helloboot/api/resourceNotFound | jq .
	@RequestMapping("/resourceNotFound")
	public void throwException() {
		Person p = new Person("g_exception_test", 33);
		Map<String, Object> data = new HashMap<>();
		data.put("PersonName:", p.getName());
		data.put("PersonAge:", p.getAge());
		throw new ResourceNotFoundException(data);
	}

}
