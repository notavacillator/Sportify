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

import com.sportify.entities.Sport;
import com.sportify.exceptions.SportifyAppExceptions;
import com.sportify.service.SportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/sport")
@CrossOrigin(origins = "http://localhost:3000")
public class SportController {

	@Autowired
	public SportService sportService;

	@Operation(summary = "Gets all sports")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sports list retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping
	public ResponseEntity<List<Sport>> getAllSports() {
		return new ResponseEntity<List<Sport>>(sportService.getAllSports(), HttpStatus.OK);
	}

	@Operation(summary = "Adds a new sport")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "New sport added", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/add")
	public ResponseEntity<Sport> addSport(@RequestBody Sport sport) {
		return new ResponseEntity<Sport>(sportService.addSport(sport), HttpStatus.CREATED);
	}

	@Operation(summary = "Deletes a sport")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sport deleted", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/delete")
	public ResponseEntity<Sport> deletSportById(@RequestBody Sport sport) {
		return new ResponseEntity<Sport>(sportService.deletSportById(sport), HttpStatus.OK);
	}
	
	@GetMapping("/{sport_id}")
	public ResponseEntity<Sport> getSportById(@PathVariable Integer sport_id) throws SportifyAppExceptions {
		return new ResponseEntity<Sport>(sportService.getSportById(sport_id), HttpStatus.OK);
	}

}