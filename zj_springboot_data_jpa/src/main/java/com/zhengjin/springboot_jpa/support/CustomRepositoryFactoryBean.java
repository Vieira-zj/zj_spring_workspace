package com.zhengjin.springboot_jpa.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new CustomRepositoryFactory(entityManager);
	}

	private static class CustomRepositoryFactory extends JpaRepositoryFactory {

		public CustomRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		/**
		 * 获得当前自定义的Repository实现。
		 */
		@Override
		@SuppressWarnings("unchecked")
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
				RepositoryInformation infomation, EntityManager entityManager) {
			return new CustomRepositoryImpl<T, ID>((Class<T>) infomation.getDomainType(), entityManager);
		}

		/**
		 * 获得当前自定义的Repository实现的类型。
		 */
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return CustomRepositoryImpl.class;
		}
	}

}
