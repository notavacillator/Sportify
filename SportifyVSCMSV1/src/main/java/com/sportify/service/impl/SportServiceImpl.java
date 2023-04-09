package com.sportify.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.entities.Sport;
import com.sportify.exceptions.SportifyAppExceptions;
import com.sportify.repository.SportRepository;
import com.sportify.service.SportService;

@Service
@Transactional
public class SportServiceImpl implements SportService {

	@Autowired
	public SportRepository sportRepository;

	@Override
	public Sport addSport(Sport sport) {
		return sportRepository.save(sport);
	}

	@Override
	public List<Sport> getAllSports() {
		return sportRepository.findAll();
	}


	@Override
	public Sport deletSportById(Sport sport) {
		Optional<Sport> sportOptional = sportRepository.findById(sport.getSportId());
		Sport deletedSport = sportOptional.orElseThrow();
		sportRepository.deleteById(sport.getSportId());
		return deletedSport;
	}

	@Override
	public Sport getSportById(Integer sport_id) throws SportifyAppExceptions {
		Optional<Sport> sport = sportRepository.findById(sport_id);
		return sport.orElseThrow(()->new SportifyAppExceptions("No Such Sport Found"));
	}

}
