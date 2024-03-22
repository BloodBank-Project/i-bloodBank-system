package com.patient.patientRequestExceptions;

public class PatientRequestDeleteException extends RuntimeException{

	public PatientRequestDeleteException() {
		super();
	}

	public PatientRequestDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientRequestDeleteException(String message) {
		super(message);
	}

}
