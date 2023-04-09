package com.sportify.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sportify.dto.UserAdminDTO;
import com.sportify.entities.Report;
import com.sportify.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public AdminService adminService;
	
	//block user
	@Operation(summary = "Block user")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User blocked.", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/blockUser")
	public ResponseEntity<UserAdminDTO> blockUser(@RequestBody UserAdminDTO userAdminDTO) {
		return new ResponseEntity<UserAdminDTO>(adminService.blockUser(userAdminDTO),HttpStatus.OK);
	}

	@Operation(summary = "Get list of all users")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List of all users retrieved.", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserAdminDTO>> getAllUsers() {
		return new ResponseEntity<List<UserAdminDTO>>(adminService.getAllUsers(),HttpStatus.OK);
	}
	
	@Operation(summary = "Get user reports")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User reports fetched.", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/viewReports")
	public ResponseEntity<List<Report>> getReports() {
		return new ResponseEntity<List<Report>>(adminService.getAllReports(),HttpStatus.OK);
	}
	
}