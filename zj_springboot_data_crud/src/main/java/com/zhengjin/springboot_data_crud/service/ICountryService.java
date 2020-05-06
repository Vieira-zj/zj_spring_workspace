package com.zhengjin.springboot_data_crud.service;

import java.util.List;

import com.zhengjin.springboot_data_crud.model.Country;

public interface ICountryService {

	List<Country> findAll();

}
