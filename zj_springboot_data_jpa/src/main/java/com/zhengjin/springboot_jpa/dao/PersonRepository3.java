package com.zhengjin.springboot_jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.zhengjin.springboot_jpa.domain.Person;

/**
 * Spring Data REST 数据访问接口。
 *
 */
//@RepositoryRestResource(path = "people")
public interface PersonRepository3 extends JpaRepository<Person, Long> {

	// path默认为在实体类之后加s, 即 persons
	// 可通过注解 @RepositoryRestResource 对path属性进行修改，更新为 people

	// spring.data.rest.base-path=/api 设置base-path为 /api, 则 path=/api/people

	// 1. 列表数据 curl -v "http://localhost:8081/api/persons"
	// 2. 单一数据 curl -v "http://localhost:8081/api/persons/1"
	// 3. 查询 curl -v "http://localhost:8081/api/persons/search/nameStartsWith?name=x"
	// 4. 分页 curl -v "http://localhost:8081/api/persons?page=1&size=2"
	// 5. 排序 curl -v "http://localhost:8081/api/persons?sort=age,desc"

	// 6. 保存数据 curl -v -XPOST "http://localhost:8081/api/persons" \
	// -H "Content-Type:application/json" -d '{"name":"cc", "address":"shengzhen", "age":24}'
	// 7. 更新数据 curl -v -XPUT "http://localhost:8081/api/persons/6" \
	// -H "Content-Type:application/json" -d '{"name":"cc", "address":"shengzhen", "age":34}'
	// 8. 删除数据 curl -v -XDELETE "http://localhost:8081/api/persons/6"

	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
	Person findByNameStartsWith(@Param("name") String name);

}
