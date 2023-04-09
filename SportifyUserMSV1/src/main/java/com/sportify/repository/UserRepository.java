package com.sportify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmailId(String emailId);
	Optional<User> findByName(String userName);
}