package com.bloodbank.exception;

public class BloodBankUpdateException extends RuntimeException {
	
	public BloodBankUpdateException(String message) {
		super(message);
	}

	public BloodBankUpdateException(String message, Exception exception) {
		super(message, exception);
	}
}