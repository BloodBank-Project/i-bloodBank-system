package com.project.bloodBank.donorExceptions;

public class DonorUpdateException extends RuntimeException {

	public DonorUpdateException(String message) {
		super(message);
	}

	public DonorUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
}
