package com.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5745704932264852551L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
}
