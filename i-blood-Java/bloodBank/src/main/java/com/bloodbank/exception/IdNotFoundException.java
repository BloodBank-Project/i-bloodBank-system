package com.bloodbank.exception;

public class IdNotFoundException extends RuntimeException {
	
	private String message;
	
	public IdNotFoundException(String message) {
		super(message);
	}
		
	public String getMessage() {
		return message;
	}

}
