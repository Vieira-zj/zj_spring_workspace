package com.zhengjin.springboot_data_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengjin.springboot_data_crud.model.Country;
import com.zhengjin.springboot_data_crud.repo.CountryRepository;

@Service
public class CountryService implements ICountryService {

	@Autowired
	private CountryRepository repository;

	@Override
	public List<Country> findAll() {
		return (List<Country>) this.repository.findAll();
	}

}
