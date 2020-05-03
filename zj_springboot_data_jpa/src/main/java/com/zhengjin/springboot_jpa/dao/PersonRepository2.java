package com.zhengjin.springboot_jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhengjin.springboot_jpa.domain.Person;
import com.zhengjin.springboot_jpa.support.CustomRepository;

/**
 * 继承CustomRepository接口，即可使用在自定义Repository中实现的功能。
 *
 */
public interface PersonRepository2 extends CustomRepository<Person, Long> {

	List<Person> findByAddress(String address);

	Person findByNameAndAddress(String name, String address);

	@Query("select p from Person p where p.name = :name and p.address = :address")
	Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

	Person withNameAndAddressNamedQuery(String name, String address);

}
