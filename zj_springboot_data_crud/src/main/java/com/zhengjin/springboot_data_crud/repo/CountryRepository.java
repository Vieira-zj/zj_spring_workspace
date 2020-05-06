package com.zhengjin.springboot_data_crud.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zhengjin.springboot_data_crud.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
