package com.sportify.service;

import java.util.List;

import com.sportify.dto.VenueDTO;
import com.sportify.entities.Venue;

public interface VenueService {

	public List<Venue> getVenueByCity(Integer city_id );
	public VenueDTO getVenueById(Integer venue_id);
	public List<Venue> getVenueBySport(Integer city_id );
	public Venue addVenue(Venue venue);
	public Venue updateVenue(Venue venue);
	public Venue deleteVenueById(Venue venue);
	
}