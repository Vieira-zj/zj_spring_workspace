package com.zhengjin.springboot_jpa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhengjin.springboot_jpa.dao.PersonRepository;
import com.zhengjin.springboot_jpa.domain.Person;

/**
 * @SpringApplicationConfiguration 替代 @ContextConfiguration 来配置 Spring Boot 的
 *                                 Application Context.
 * @Transactional 每次测试后的数据被回滚。
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Ch82Application.class)
@WebAppConfiguration
@Transactional
public class Ch82ApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mvc;
	String expectedJson;

	@Before
	public void setUp() throws JsonProcessingException {
		Person p1 = this.personRepository.save(new Person(new Long(0), "cc", new Integer(33), "wuhan"));
		Person p2 = this.personRepository.save(new Person(new Long(0), "dd", new Integer(33), "shanghai"));
		System.out.println("save person with id: " + p1.getId());
		System.out.println("save person with id: " + p2.getId());

		this.expectedJson = this.Obj2Json(this.personRepository.findAll());
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	protected String Obj2Json(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(obj);
	}

	@Test
	public void testDataController() throws Exception {
		String uri = "/test";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = result.getResponse().getStatus();
		Assert.assertEquals("Return status code is NOT 200.", 200, status);
		String content = result.getResponse().getContentAsString();
		Assert.assertEquals("Return response body is NOT as expected.", expectedJson, content);
	}

}
