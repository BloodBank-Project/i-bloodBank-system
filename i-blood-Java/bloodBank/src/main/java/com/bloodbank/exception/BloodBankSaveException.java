package com.bloodbank.exception;

public class BloodBankSaveException  extends RuntimeException {
	public BloodBankSaveException(String message) {
		super(message);
	}

	public BloodBankSaveException(String message, Exception exception) {
		super(message, exception);
	}

}