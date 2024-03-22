package com.user.exception;

public class UserUpdateException extends RuntimeException{
	
	private static final long serialVersionUID = 8487974348653372018L;

	public UserUpdateException(String message) {
		super(message);
	}
	
	public UserUpdateException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
