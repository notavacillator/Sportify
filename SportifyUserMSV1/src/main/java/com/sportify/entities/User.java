package com.sportify.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	private String name;

	private Date dob;

	private Integer city;

	@Email()
	@Column(unique = true)
	private String emailId;

	private String password;

	private String phoneNo;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private Boolean blockedByAdmin = false;

	private Integer strikes;

	private String profileImage;

	private byte isAdmin;

	@Column(nullable = false, columnDefinition = "TINYINT default 0")
	public byte getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public User() {

	}

	public User(Integer userId, String name, Date dob, Integer city, @Email String emailId, String password,
			String phoneNo, Gender gender, Boolean blockedByAdmin, Integer strikes, String profileImage, byte isAdmin) {
		super();
		this.userId = userId;
		this.name = name;
		this.dob = dob;
		this.city = city;
		this.emailId = emailId;
		this.password = password;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.blockedByAdmin = blockedByAdmin;
		this.strikes = strikes;
		this.profileImage = profileImage;
		this.isAdmin = isAdmin;
	}

	public void updateDetails(User u) {
		this.setUserId(u.getUserId());
		this.setBlockedByAdmin(u.getBlockedByAdmin());
		this.setCity(u.getCity());
		this.setDob(u.getDob());
		this.setEmailId(u.getEmailId());
		this.setGender(u.getGender());
		this.setName(u.getName());
		this.setPassword(u.getPassword());
		this.setPhoneNo(u.getPhoneNo());
		this.setStrikes(u.getStrikes());
		this.setProfileImage(u.getProfileImage());
		this.setIsAdmin(u.getIsAdmin());
	}
}