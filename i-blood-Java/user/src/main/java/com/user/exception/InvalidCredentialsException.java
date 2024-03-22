package com.user.exception;

public class InvalidCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 3278024878471403822L;

	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
}
