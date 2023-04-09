package com.sportify.service;

import java.util.List;

import com.sportify.entities.Sport;
import com.sportify.exceptions.SportifyAppExceptions;

public interface SportService {
	public Sport addSport(Sport sport);
	public List<Sport> getAllSports();
	public Sport deletSportById(Sport sport);
	public Sport getSportById(Integer sport_id) throws SportifyAppExceptions;
	
}
