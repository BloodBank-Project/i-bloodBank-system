package com.user.exception;

public class NoUsersFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -4730386867152288687L;

	public NoUsersFoundException(String message) {
		super(message);
	}
	
	public NoUsersFoundException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
