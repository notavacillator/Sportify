package com.sportify.dto;

import com.sportify.entities.Gender;
import com.sportify.entities.User;

public class UserDTO {

	private Integer userId;
	private String name;
	private String emailId;
	private Gender gender;
	private String profileImage;
	private byte isAdmin;

	public UserDTO() {
		super();
	}

	public UserDTO(Integer userId, String name, String emailId, Gender gender,String profileImage, byte isAdmin) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.gender = gender;
		this.isAdmin = isAdmin;
		this.profileImage = profileImage;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
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

	public static UserDTO convertToDTO(User user) {
		System.out.println("found " + user.getName());
		System.out.println("found " + user.getUserId());
		System.out.println("found " + user.getEmailId());
		System.out.println("found " + user.getGender());
		return new UserDTO(user.getUserId(), user.getName(), user.getEmailId(), user.getGender(), user.getProfileImage(), user.getIsAdmin());
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", gender=" + gender + "]";
	}

}