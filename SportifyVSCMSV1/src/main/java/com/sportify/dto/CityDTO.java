package com.sportify.dto;

public class CityDTO {

	private Integer cityId;
	private String name;
	private String state;

	public CityDTO() {
		super();
	}

	public CityDTO(Integer cityId, String name, String state) {
		super();
		this.cityId = cityId;
		this.name = name;
		this.state = state;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
