package com.sportify.dto;

public class VenueDTO {
	private Integer venueId;
	private String groundName;
	private String address;
	private String landmark;
	private CityDTO city;
	private Integer pincode;
	private String timings;

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

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	@Override
	public String toString() {
		return "VenueDTO [venueId=" + venueId + ", groundName=" + groundName + ", address=" + address + ", landmark="
				+ landmark + ", city=" + city + ", pincode=" + pincode + ", timings=" + timings + "]";
	}

	
}
