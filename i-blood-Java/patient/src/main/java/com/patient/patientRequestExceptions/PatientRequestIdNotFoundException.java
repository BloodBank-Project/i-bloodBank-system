package com.patient.patientRequestExceptions;

public class PatientRequestIdNotFoundException extends RuntimeException {

	public PatientRequestIdNotFoundException() {
		super();

	}

	public PatientRequestIdNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public PatientRequestIdNotFoundException(String message) {
		super(message);

	}

}
