package com.sportify.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
	private LocalDateTime timestamp;
	private String message;
	
	public ErrorResponse(LocalDateTime timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	public ErrorResponse() {
		super();
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}