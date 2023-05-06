package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

/**
 * It is a Class for Error messaging. It has only constructor, Getters & Setters.
 * There is no Injection here.
 */
public class ErrorDetails {
	//Timestamp
	private LocalDateTime timestamp;
	
	//error message
	private String message;
	
	//details
	private String details;

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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	

}
