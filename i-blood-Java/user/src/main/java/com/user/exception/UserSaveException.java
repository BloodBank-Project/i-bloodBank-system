package com.user.exception;

public class UserSaveException extends RuntimeException {

	private static final long serialVersionUID = -2746639928175692971L;

	public UserSaveException(String message) {
		super(message);
	}
	
	public UserSaveException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
