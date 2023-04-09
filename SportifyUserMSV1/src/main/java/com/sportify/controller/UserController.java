package com.sportify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.dto.UserDTO;
import com.sportify.entities.Report;
import com.sportify.entities.User;
import com.sportify.models.AuthenticationResponse;
import com.sportify.service.UserService;
import com.sportify.utils.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	// input - email, pwd
	// returns - new user object
	@Operation(summary = "Sign Up")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sign Up Done", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/signup")
	public UserDTO signUp(@RequestBody User user) {
		return userService.signUp(user);
	}

	// input - email pwd
	// process - validate login
	// returns - user object
	@Operation(summary = "Login")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login Successful", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/authenticate")
	public ResponseEntity<?> Login(@RequestBody User user) throws Exception {
		try {
			System.out.println("user email " + user.getEmailId());
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		final UserDetails userDetails = userService.loadUserByUsername(user.getEmailId());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(userService.login(user), jwt));
	}

	// input - user object
	// returns - user object
	@Operation(summary = "Edit Profile")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Profile details updated.", content = {
			@Content(mediaType = "application/json") }) })
	@PutMapping("/editProfile")
	public ResponseEntity<User> editProfile(@RequestBody User user) {
		return new ResponseEntity<User>(userService.editProfile(user), HttpStatus.OK);
	}

	@Operation(summary = "Get user info by Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Info Retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/{user_id}")
	public ResponseEntity<UserDTO> getUserById( @PathVariable Integer user_id) {
		return new ResponseEntity<UserDTO>(userService.getUserById(user_id), HttpStatus.OK);
	}

	@Operation(summary = "Submit Report")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User report submitted", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/report")
	public ResponseEntity<Report> submitReport(@RequestBody Report userReport) {
		return new ResponseEntity<Report>(userService.submitReport(userReport), HttpStatus.CREATED);
	}
}