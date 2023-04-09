package com.sportify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	public List<Event> findByCityId(Integer cityId);
	public List<Event> findByCreatorUserId(Integer userId);
}