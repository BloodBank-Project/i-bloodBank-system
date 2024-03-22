package com.bloodbank.exception;

public class BloodBankFetchException extends RuntimeException {
	
	public BloodBankFetchException(String message) {
		super(message);
	}

	public BloodBankFetchException(String message, Exception exception) {
		super(message, exception);
	}
}