package com.example.exeptions;

import java.util.Date;

public class CustomErrorDetails {

	private String errorDetails;
	private String message;
	private Date timestamp;

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public CustomErrorDetails(String errorDetails, String message, Date timestamp) {
		this.errorDetails = errorDetails;
		this.message = message;
		this.timestamp = timestamp;
	}

}
