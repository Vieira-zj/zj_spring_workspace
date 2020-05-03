package com.zhengjin.springboot_jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 在实体类中定义的数据类型要用包装类型（Long, Integer），而不能使用原始数据类型（long, int）。在Spring MVC中，
 * 使用原始数据类型会自动初始化为0, 而不是空。
 * 
 */
@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name = ?1 and address =?2")
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private Integer age;
	private String address;

	public Person() {
		super();
	}

	public Person(Long id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
