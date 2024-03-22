package com.blood.exception;

public class NoDataFoundException extends RuntimeException {

	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(String message, Exception exception) {
		super(message, exception);
	}
}
