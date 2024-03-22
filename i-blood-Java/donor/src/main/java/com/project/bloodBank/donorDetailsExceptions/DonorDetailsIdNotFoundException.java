package com.project.bloodBank.donorDetailsExceptions;

public class DonorDetailsIdNotFoundException extends RuntimeException {

	public DonorDetailsIdNotFoundException(String message) {
		super(message);
	}

	public DonorDetailsIdNotFoundException(String message, Exception exception) {
		super(message, exception);
	}

}
