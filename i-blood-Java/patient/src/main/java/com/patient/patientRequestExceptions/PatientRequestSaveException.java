package com.patient.patientRequestExceptions;

public class PatientRequestSaveException extends RuntimeException {

	public PatientRequestSaveException(String message) {
		super(message);
	}

	public PatientRequestSaveException(String message, Exception exception) {
		super(message, exception);
	}

}
