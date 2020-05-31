package com.zhengjin.springboot_jpa.support;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * JpaSpecificationExecutor让我们具备使用Specification的能力。
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	List<T> findAllByAddressWuHan();

	Page<T> findByAuto(T example, Pageable pageable);

}
