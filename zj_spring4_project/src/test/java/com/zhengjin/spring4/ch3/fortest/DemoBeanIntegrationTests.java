package com.zhengjin.spring4.ch3.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @ContextConfiguration用来加载配置ApplicationContext, 其中classes属性用来加载配置类
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@ActiveProfiles("prod")
public class DemoBeanIntegrationTests {

	@Autowired
	private TestBean testBean;

	@Test
	public void prodBeanShouldInject() {
		String expect = "from production profile";
		String actual = testBean.getContent();
		Assert.assertEquals(expect, actual);
	}

}
