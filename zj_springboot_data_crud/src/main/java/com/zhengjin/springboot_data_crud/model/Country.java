package com.zhengjin.springboot_data_crud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Integer population;

	public Country() {
	}

	public Country(Long id, String name, Integer population) {
		this.id = id;
		this.name = name;
		this.population = population;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return String.format("Country{id=%d, name=%s, population=%d}", this.id, this.name, this.population);
	}

}
