package com.patient.patientExceptions;

public class PatientDetailsNotFoundException extends RuntimeException {

	public PatientDetailsNotFoundException() {
		super();

	}

	public PatientDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public PatientDetailsNotFoundException(String message) {
		super(message);

	}

}
