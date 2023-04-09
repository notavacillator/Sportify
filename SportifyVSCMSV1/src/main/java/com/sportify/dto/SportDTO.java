package com.sportify.dto;

public class SportDTO {
	private Integer sportId;
	private String sportName;

	public SportDTO() {
		super();
	}

	public SportDTO(Integer sportId, String sportName) {
		super();
		this.sportId = sportId;
		this.sportName = sportName;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

}
