package com.user.exception;

public class UserDeleteException extends RuntimeException {

	private static final long serialVersionUID = 7894021831618296372L;

	public UserDeleteException(String message) {
		super(message);
	}
	
	public UserDeleteException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
