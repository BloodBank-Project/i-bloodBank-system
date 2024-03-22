package com.patient.patientRequestExceptions;

public class PatientRequestDetailsNotFoundException extends RuntimeException {

	public PatientRequestDetailsNotFoundException() {
		super();
	}

	public PatientRequestDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public PatientRequestDetailsNotFoundException(String message) {
		super(message);

	}

}
