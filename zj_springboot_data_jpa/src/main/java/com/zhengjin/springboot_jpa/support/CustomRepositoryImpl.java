package com.zhengjin.springboot_jpa.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import static com.zhengjin.springboot_jpa.specs.CustomerSpecs.*;

/**
 * 继承SimpleJpaRepository类让我们可以使用其提供的方法（如findAll）。
 *
 * @param <T>
 * @param <ID>
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements CustomRepository<T, ID> {

	private EntityManager entityManager;

	public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findAllByAddressWuHan() {
		return this.findAll(byAddressWuhan());
	}

	// 数据访问操作
	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {
		return this.findAll(byAuto(this.entityManager, example), pageable);
	}

}
