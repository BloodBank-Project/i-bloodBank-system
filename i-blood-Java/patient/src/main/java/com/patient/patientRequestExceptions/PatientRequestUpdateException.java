package com.patient.patientRequestExceptions;

public class PatientRequestUpdateException extends RuntimeException {

	public PatientRequestUpdateException() {
		super();
	}

	public PatientRequestUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientRequestUpdateException(String message) {
		super(message);
	}

}
