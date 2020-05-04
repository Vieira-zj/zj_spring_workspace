package com.zhengjin.springboot_data_redis.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.zhengjin.springboot_data_redis.domain.Person;

@Repository
public class PersonDao {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps;

	public void setString() {
		valOpsStr.set("aa", "bb");
	}

	public String getString() {
		return valOpsStr.get("aa");
	}

	public void save(Person person) {
		valOps.set(person.getId(), person);
	}

	public Person getPerson() {
		return (Person) valOps.get("1");
	}

}
