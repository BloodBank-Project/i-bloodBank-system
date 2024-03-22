package com.patient.patientExceptions;

public class PatientSaveException extends RuntimeException {

	public PatientSaveException(String message) {
		super(message);
	}

	public PatientSaveException(String message, Exception exception) {
		super(message, exception);
	}

}
