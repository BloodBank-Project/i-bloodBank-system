package com.bloodbank.exception;

public class BloodBankDeleteException  extends RuntimeException {
	
	public BloodBankDeleteException(String message) {
		super(message);
	}

	public BloodBankDeleteException(String message, Exception exception) {
		super(message, exception);
	}
}