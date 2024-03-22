package com.patient.patientExceptions;

public class PatientUpdateException extends RuntimeException {

	public PatientUpdateException() {
		super();
	}

	public PatientUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientUpdateException(String message) {
		super(message);
	}

}
