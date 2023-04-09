package com.sportify.dto;

import java.util.ArrayList;
import java.util.List;

import com.sportify.entities.City;
import com.sportify.entities.Venue;

public class VenueDTO {

	private Integer venueId;
	private String groundName;
	private String address;
	private String landmark;
	private CityDTO city;
	private Integer pincode;
	private String facilities;
	private String timings;
	private List<SportDTO> sportsAllowed = new ArrayList<>();

	public VenueDTO() {
		super();
	}

	public VenueDTO(Integer venueId, String groundName, String address, String landmark, CityDTO city, Integer pincode,
			String facilities, String timings, List<SportDTO> sportsAllowed) {
		super();
		this.venueId = venueId;
		this.groundName = groundName;
		this.address = address;
		this.landmark = landmark;
		this.city = city;
		this.pincode = pincode;
		this.facilities = facilities;
		this.timings = timings;
		this.sportsAllowed = sportsAllowed;
	}

	public List<SportDTO> getSportsAllowed() {
		return sportsAllowed;
	}

	public void setSportsAllowed(List<SportDTO> sportsAllowed) {
		this.sportsAllowed = sportsAllowed;
	}

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public static VenueDTO convertToDTO(Venue v) {
		List<SportDTO> sportDtos = new ArrayList<>();
		v.getSportsAllowed().forEach(sport -> sportDtos.add(new SportDTO(sport.getSportId(), sport.getSportName())));
		City city = v.getCity();
		CityDTO cityDto = new CityDTO(city.getCityId(), city.getName(), city.getState());
		return new VenueDTO(v.getVenueId(), v.getGroundName(), v.getAddress(), v.getLandmark(), cityDto, v.getPincode(),
				v.getFacilities(), v.getTimings(), sportDtos);
	}
}
