package com.project.bloodBank.donorExceptions;

public class DonorIdNotFoundException extends RuntimeException {

	public DonorIdNotFoundException(String message) {
		super(message);
	}

	public DonorIdNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

}
