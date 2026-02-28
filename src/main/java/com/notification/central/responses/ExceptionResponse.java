package com.notification.central.responses;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private int status;
	private LocalDateTime timestamp;
	private String error;
	private String message;
	
	public ExceptionResponse() {
	}
	
	public ExceptionResponse(int status, String error, String message) {
		this.status = status;
		this.timestamp = LocalDateTime.now();
		this.error = error;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}