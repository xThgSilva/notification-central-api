package com.notification.central.exceptions;

public class SameSenderRecipientIdException extends RuntimeException{
	public SameSenderRecipientIdException(String message) {
		super(message);
	}
}
