package com.sportify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportify.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

	@Query(value="Select * from Venue where city_id = :city_id", nativeQuery = true)
	List<Venue> findVenueByCity(Integer city_id);
	
//	SELECT o FROM Order o JOIN FETCH o.customer WHERE o.customer.id = :customerId

	//select * from venue join venue_sport on venue.id = venue_sport.venue_id 
	//where venue_sport.sport_id = :sport_id
	
//	@Query("SELECT v from venue v JOIN venue_sport on v.venue_id = venue_sport.venue_id"
//			+ "where venue_sport.sport_id = :sport_id")
	
	@Query(value = "select * from venue join venue_sport on venue.venue_id = venue_sport.venue_id "
			+ "where venue_sport.sport_id = :sport_id",nativeQuery = true)
	List<Venue> findBySport(Integer sport_id);
	
	
}