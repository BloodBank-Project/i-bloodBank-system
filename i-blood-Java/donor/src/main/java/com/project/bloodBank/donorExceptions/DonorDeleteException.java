package com.project.bloodBank.donorExceptions;

public class DonorDeleteException extends RuntimeException {

	public DonorDeleteException(String message) {
		super(message);
	}

	public DonorDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

}
