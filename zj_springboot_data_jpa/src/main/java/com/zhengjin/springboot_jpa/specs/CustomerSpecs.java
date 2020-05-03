package com.zhengjin.springboot_jpa.specs;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSpecs {

	// T is Person
	public static <T> Specification<T> byAddressWuhan() {
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("address"), "wuhan");
			}
		};
	}

	/**
	 * 输入的查询条件（/auto?address=han&age=30）转化为Person实体类（entity）
	 * 
	 * @param entityManager
	 * @param example       T (type) as Person
	 * @return
	 */
	public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {
		@SuppressWarnings("unchecked")
		final Class<T> type = (Class<T>) example.getClass();

		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>(); // 查询条件
				EntityType<T> entity = entityManager.getMetamodel().entity(type); // 实体类，包含属性（name,age）

				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
					Object attrValue = getValue(example, attr); // 获得属性值
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) {
							if (!StringUtils.isEmpty(attrValue)) {
								predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue))); // 构造属性值like查询条件
							}
						} else {
							predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue)); // 构造属性值equal查询条件
						}
					}
				}
				return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));
			}
		};
	}

	// 获得实体类对象属性的值
	static private <T> Object getValue(T example, Attribute<T, ?> attr) {
		return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
	}

	// 获得实体类的当前属性的SingularAttribute（包含的是实体类的某个单独属性）
	static private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
			Class<E> fieldClass) {
		return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
	}

	static private String pattern(String str) {
		return "%" + str + "%";
	}

}
