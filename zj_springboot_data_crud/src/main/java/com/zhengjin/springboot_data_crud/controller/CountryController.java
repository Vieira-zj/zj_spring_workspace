package com.zhengjin.springboot_data_crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhengjin.springboot_data_crud.model.Country;
import com.zhengjin.springboot_data_crud.service.ICountryService;

@Controller
public class CountryController {

	@Autowired
	private ICountryService countryService;

	// path: /repo/index.html
	@GetMapping("/countries")
	public ModelAndView getCountries() {
		List<Country> countries = countryService.findAll();
		Map<String, Object> params = new HashMap<>();
		params.put("countries", countries);
		return new ModelAndView("showCountries", params);
	}

}
