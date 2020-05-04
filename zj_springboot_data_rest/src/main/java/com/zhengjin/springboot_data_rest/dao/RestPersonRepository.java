package com.zhengjin.springboot_data_rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.zhengjin.springboot_data_rest.domain.Person;

/**
 * Spring Data REST 数据访问接口。
 *
 */
@RepositoryRestResource(path = "people")
public interface RestPersonRepository extends JpaRepository<Person, Long> {

	// path默认为在实体类之后加s, 即 persons
	// 可通过注解 @RepositoryRestResource 对path属性进行修改，更新为 people

	// spring.data.rest.base-path=/api 设置base-path为 /api, 则 path=/api/people

	// 1. 列表数据 curl -v "http://localhost:8081/api/people"
	// 2. 单一数据 curl -v "http://localhost:8081/api/people/1"
	// 3. 查询 curl -v "http://localhost:8081/api/people/search/nameStartsWith?name=y"
	// 4. 分页 curl -v "http://localhost:8081/api/people?page=1&size=2"
	// 5. 排序 curl -v "http://localhost:8081/api/people?sort=age,desc"

	// 6. 保存数据 curl -v -XPOST "http://localhost:8081/api/people" \
	// -H "Content-Type:application/json" -d '{"name":"cc", "address":"shengzhen", "age":24}'
	// 7. 更新数据 curl -v -XPUT "http://localhost:8081/api/people/{id}" \
	// -H "Content-Type:application/json" -d '{"name":"cc", "address":"shengzhen", "age":34}'
	// 8. 删除数据 curl -v -XDELETE "http://localhost:8081/api/people/{id}"

	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
	Person findByNameStartsWith(@Param("name") String name);

}
