package com.sportify.service;

import java.util.List;

import com.sportify.entities.City;

public interface CityService {

	public List<City> viewCities();
	public City addCity(City city);
	public City deleteCity(City city);
}
