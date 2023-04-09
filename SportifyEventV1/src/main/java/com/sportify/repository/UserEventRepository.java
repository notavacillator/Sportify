package com.sportify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportify.entities.Event;
import com.sportify.entities.UserEvents;

public interface UserEventRepository extends JpaRepository<UserEvents, Integer> {

	public void deleteByEvent(Event event);

	public List<UserEvents> findByUserId(Integer user_id);
	
	public List<UserEvents> findByEvent(Event event);
	
	public UserEvents findByUserIdAndEvent(Integer userId, Event event);
	
	@Query(value="select * from user_events u join event e using (event_id)",nativeQuery = true)
	public List<UserEvents> findPastEventByUserId(Integer user_id);
}