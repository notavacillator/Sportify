package com.sportify.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venue")
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venue_id")
	private Integer venueId;

	@Column(name = "ground_name")
	private String groundName;
	private String address;
	private String landmark;

	@JoinColumn(name = "city_id")
	@ManyToOne()
	private City city;

	private Integer pincode;

	@ManyToMany()
	@JoinTable(name = "venue_sport", joinColumns = @JoinColumn(name = "venue_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
	private List<Sport> sportsAllowed = new ArrayList<>();

	@Column(length = 1200)
	private String facilities;

	@Column(length = 500)
	private String timings;

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venue_id) {
		this.venueId = venue_id;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public List<Sport> getSportsAllowed() {
		return sportsAllowed;
	}

	public void setSportsAllowed(List<Sport> sportsAllowed) {
		this.sportsAllowed = sportsAllowed;
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

	public void updateVenue(Venue venue) {
		this.setAddress(venue.getAddress());
		this.setCity(venue.getCity());
		this.setFacilities(venue.getFacilities());
		this.setGroundName(venue.getGroundName());
		this.setLandmark(venue.getLandmark());
		this.setPincode(venue.getPincode());
		this.setSportsAllowed(venue.getSportsAllowed());
		this.setTimings(venue.getTimings());
		this.setTimings(venue.getTimings());
	}

//	{
//	    "groundName": "Shivaji Ground Camp",
//	    "address": "address",
//	    "landmark": "big building",
//	    "city": {
//	        "cityId": 1,
//	        "name": "BHILAI",
//	        "state": "CHHATTISGARH"
//	    },
//	    "pincode": 100101,
//	    "facilities": "USP",
//	    "timings": "Monday-Saturday : 6 AM- 10-PM, Sunday : Closed",
//	    "sportsAllowed": [
//	        {
//	            "sportId": 1,
//	            "sportName": "CRICKET"
//	        },
//	        {
//	            "sportId": 2,
//	            "sportName": "FOOTBALL"
//	        }
//	    ]
//	}

}