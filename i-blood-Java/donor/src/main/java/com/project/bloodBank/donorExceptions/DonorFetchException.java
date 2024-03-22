package com.project.bloodBank.donorExceptions;

public class DonorFetchException extends RuntimeException {

	public DonorFetchException(String message) {
		super(message);
	}

	public DonorFetchException(String message, Throwable cause) {
		super(message, cause);
	}
}
