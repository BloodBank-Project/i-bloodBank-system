package com.project.bloodBank.donorDetailsExceptions;

public class DonorDetailsDeleteException extends RuntimeException {

	public DonorDetailsDeleteException(String message) {
		super(message);
	}

	public DonorDetailsDeleteException(String message, Throwable cause) {
		super(message, cause);
	}
}
