package com.sportify.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.dto.VenueDTO;
import com.sportify.entities.Venue;
import com.sportify.repository.VenueRepository;
import com.sportify.service.VenueService;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venueRepository;

	// will be used in create/edit event.
	// return list of venue
	public List<Venue> getVenueByCity(Integer city_id) {
		// TODO - exception handling when venuelist is empty
		List<Venue> venueList = venueRepository.findVenueByCity(city_id);
		return venueList;
	}

	// will be used in browse event
	public VenueDTO getVenueById(Integer venue_id) {
		Optional<Venue> venue = venueRepository.findById(venue_id);
		// TODO - exception handling when venue is empty
		return VenueDTO.convertToDTO(venue.orElseThrow());
	}

	// takes sport id as param
	// returns list of venues
	public List<Venue> getVenueBySport(Integer sport_id) {
		return venueRepository.findBySport(sport_id);

//		List<Venue> venueList = venueRepository.findAll();
//		
//		Optional<Sport> sport= sportRepository.findById(sport_id);
//		
//		venueList=venueList.stream()
//				.filter(x->x.getSport_allowed()
//				.contains(sport.get()))
//				.collect(Collectors.toList());
//		return venueList;
	}

	@Override
	public Venue addVenue(Venue venue) {
		return venueRepository.save(venue);
	}

	@Override
	public Venue updateVenue(Venue venue) {
		Optional<Venue> venueOptional = venueRepository.findById(venue.getVenueId());
		Venue validVenue = venueOptional.orElseThrow();
		validVenue.updateVenue(venue);
		venueRepository.flush();
		return validVenue;
	}

	@Override
	public Venue deleteVenueById(Venue venue) {
		Optional<Venue> venueOptional = venueRepository.findById(venue.getVenueId());
		Venue deletedVenue = venueOptional.orElseThrow();
		venueRepository.deleteById(venue.getVenueId());
		return deletedVenue;
	}
}