package com.zhengjin.springboot_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zhengjin.springboot_jpa.support.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class Ch82Application {

	public static void main(String[] args) {

		SpringApplication.run(Ch82Application.class, args);
	}

}
