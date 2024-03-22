package com.patient.patientExceptions;

public class PatientDeleteException extends RuntimeException {

	public PatientDeleteException() {
		super();
	}

	public PatientDeleteException(String message) {
		super(message);
	}

}
