package com.project.bloodBank.donorDetailsExceptions;

public class DonorDetailsFetchException extends RuntimeException {

	public DonorDetailsFetchException(String message) {
		super(message);
	}

	public DonorDetailsFetchException(String message, Throwable cause) {
		super(message, cause);
	}
}