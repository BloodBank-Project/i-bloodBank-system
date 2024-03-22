package com.project.bloodBank.donorExceptions;

public class DonorSaveException extends RuntimeException {

	public DonorSaveException(String message) {
		super(message);
	}

	public DonorSaveException(String message, Throwable cause) {
		super(message, cause);
	}
}
