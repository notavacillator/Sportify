package com.sportify.dto;

public class SportDTO {
	private Integer sportId;
	private String sportName;
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
	@Override
	public String toString() {
		return "SportDTO [sportId=" + sportId + ", sportName=" + sportName + "]";
	}
}
