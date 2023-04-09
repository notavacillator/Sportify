package com.sportify.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.entities.City;
import com.sportify.repository.CityRepository;
import com.sportify.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	// user will be shown all the cities options
	// after user selects the city the city name will be
	// passed as parameter to browseEvents() in eventController
	// returns list of cities.
	@Override
	public List<City> viewCities() {
		return cityRepository.findAll();
	}

	@Override
	public City addCity(City city) {
		return cityRepository.save(city);
	}

	@Override
	public City deleteCity(City city) {
		Optional<City> cityOptional = cityRepository.findById(city.getCityId());
		City deletedCity = cityOptional.orElseThrow();
		cityRepository.deleteById(city.getCityId());
		return deletedCity;
	}

}
