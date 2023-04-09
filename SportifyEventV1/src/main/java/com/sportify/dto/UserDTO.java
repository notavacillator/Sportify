package com.sportify.dto;

import com.sportify.entities.Gender;

public class UserDTO {

	private Integer userId;
	private String name;
	private String emailId;
	private Gender gender;

	public UserDTO() {
		super();
	}

	public UserDTO(Integer userId, String name, String emailId, Gender gender) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.gender = gender;
	}

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

	public UserDTO(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", gender=" + gender + "]";
	}

}