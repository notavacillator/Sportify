package com.sportify.dto;

import com.sportify.entities.Gender;

public class UserAdminDTO {

	private Integer userId;
	private String name;
	private Integer city;
	private String emailId;
	private Gender gender;
	private Boolean blockedByAdmin;
	private Integer strikes;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Boolean getBlockedByAdmin() {
		return blockedByAdmin;
	}
	public void setBlockedByAdmin(Boolean blockedByAdmin) {
		this.blockedByAdmin = blockedByAdmin;
	}
	public Integer getStrikes() {
		return strikes;
	}
	public void setStrikes(Integer strikes) {
		this.strikes = strikes;
	}
	public UserAdminDTO(Integer userId, String name, Integer city, String emailId, Gender gender,
			Boolean blockedByAdmin, Integer strikes) {
		super();
		this.userId = userId;
		this.name = name;
		this.city = city;
		this.emailId = emailId;
		this.gender = gender;
		this.blockedByAdmin = blockedByAdmin;
		this.strikes = strikes;
	}
	public UserAdminDTO() {
		super();
	}	
}
