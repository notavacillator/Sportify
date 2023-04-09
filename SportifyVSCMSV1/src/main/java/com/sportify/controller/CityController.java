package com.sportify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.entities.City;
import com.sportify.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {

	@Autowired
	public CityService cityService;

	// user will be shown all the cities options
	// after user selects the city the city name will be
	// passed as parameter to browseEvents() in eventController
	// returns list of cities.
	@Operation(summary = "Gets all cities")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "City list retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping
	public ResponseEntity<List<City>> viewCities() {
		return new ResponseEntity<List<City>>(cityService.viewCities(), HttpStatus.OK);
	}

	@Operation(summary = "Adds a City")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "New City added", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/add")
	public ResponseEntity<City> addCity(@RequestBody City city) {
		return new ResponseEntity<City>(cityService.addCity(city), HttpStatus.CREATED);
	}

	@Operation(summary = "Deletes a City")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "City Deleted", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/delete")
	public ResponseEntity<City> deleteCity(@RequestBody City city) {
		return new ResponseEntity<City>(cityService.deleteCity(city), HttpStatus.OK);
	}
}
