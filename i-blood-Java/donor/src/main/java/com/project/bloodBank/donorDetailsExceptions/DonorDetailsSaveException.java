package com.project.bloodBank.donorDetailsExceptions;

public class DonorDetailsSaveException extends RuntimeException {

	public DonorDetailsSaveException(String message) {
		super(message);
	}

	public DonorDetailsSaveException(String message, Throwable cause) {
		super(message, cause);
	}
}
