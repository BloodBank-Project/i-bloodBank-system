package com.blood.exception;

public class BloodServiceException extends RuntimeException {

	public BloodServiceException(String message) {
		super(message);
	}

	public BloodServiceException(String message, Exception exception) {
		super(message, exception);
	}
}