package com.sportify.models;

import java.io.Serializable;

import com.sportify.dto.UserDTO;

//output structure
public class AuthenticationResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDTO userDetail;
	private final String jwt;

	
	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	

	public AuthenticationResponse(UserDTO userDetail, String jwt) {
		super();
		this.userDetail = userDetail;
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}

	public UserDTO getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDTO userDetail) {
		this.userDetail = userDetail;
	}
	
	
	
}
