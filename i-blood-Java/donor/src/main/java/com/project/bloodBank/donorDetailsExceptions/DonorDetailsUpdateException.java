package com.project.bloodBank.donorDetailsExceptions;

public class DonorDetailsUpdateException extends RuntimeException {

	public DonorDetailsUpdateException(String message) {
		super(message);
	}

	public DonorDetailsUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
}
