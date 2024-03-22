package com.patient.patientExceptions;

public class PatientIdNotFoundException extends RuntimeException {

	public PatientIdNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public PatientIdNotFoundException(String message) {
		super(message);

	}

}
