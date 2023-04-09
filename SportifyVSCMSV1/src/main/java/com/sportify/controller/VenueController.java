package com.sportify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.dto.VenueDTO;
import com.sportify.entities.Venue;
import com.sportify.service.VenueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/venue")
@CrossOrigin(origins = "http://localhost:3000")
public class VenueController {

	@Autowired
	private VenueService venueService;

	// will be used in create/edit event.
	// return list of venue
	@Operation(summary = "Gets all venues of city")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue list retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/city/{city_id}")
	public ResponseEntity<List<Venue>> getVenueByCity(
			@Parameter(description = "City id for which venues are to be searched", required = true) @PathVariable(value = "city_id") Integer city_id) {
		return new ResponseEntity<List<Venue>>(venueService.getVenueByCity(city_id), HttpStatus.OK);
	}

	// will be used in browse event
	// return venue
	// it will be called by Event MS.
	@Operation(summary = "Gets a venue by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue detail retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/{venue_id}")
	public ResponseEntity<VenueDTO> getVenueById(
			@Parameter(description = "Venue id to find details of the venue", required = true) @PathVariable Integer venue_id) {
		System.out.println(venue_id + "------------------------------------------------->");
		return new ResponseEntity<VenueDTO>(venueService.getVenueById(venue_id), HttpStatus.OK);
	}

	// takes sport id as param
	// returns list of venues
	@Operation(summary = "Gets list of Venue that allows a provided sport")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue list retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/sport/{sport_id}")
	public ResponseEntity<List<Venue>> getVenueBySport(
			@Parameter(description = "Sport id to find list of the venues  that allows the provided sport", required = true) @PathVariable Integer sport_id) {
		return new ResponseEntity<List<Venue>>(venueService.getVenueBySport(sport_id), HttpStatus.OK);
	}

	@Operation(summary = "Adds a new Venue")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue details added ", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/add")
	public ResponseEntity<Venue> addVenue(@RequestBody Venue venue) {
		return new ResponseEntity<Venue>(venueService.addVenue(venue), HttpStatus.CREATED);
	}

	@Operation(summary = "Updates a venue details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue details updated", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/update")
	public ResponseEntity<Venue> updateVenue(@RequestBody Venue venue) {
		return new ResponseEntity<Venue>(venueService.updateVenue(venue), HttpStatus.OK);
	}

	@Operation(summary = "Deletes or Removes a Venue details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venue deteted", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/delete")
	public ResponseEntity<Venue> deleteVenueById(@RequestBody Venue venue) {
		return new ResponseEntity<Venue>(venueService.deleteVenueById(venue), HttpStatus.OK);
	}
}