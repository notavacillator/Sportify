package com.sportify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}