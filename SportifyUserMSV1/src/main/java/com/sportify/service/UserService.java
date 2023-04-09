package com.sportify.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.sportify.dto.UserDTO;
import com.sportify.entities.Report;
import com.sportify.entities.User;
import com.sportify.exceptions.SportifyAppExceptions;

public interface UserService extends UserDetails {
	public UserDTO signUp(User user) ;
	public UserDTO login(User user) throws SportifyAppExceptions;
	public User editProfile(User user) ;
	public UserDTO getUserById(Integer integer);
	public Report submitReport(Report userReport);
	public UserDetails loadUserByUsername(String name);
	
}