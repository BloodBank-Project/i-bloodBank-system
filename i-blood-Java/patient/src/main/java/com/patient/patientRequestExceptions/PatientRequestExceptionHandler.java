package com.patient.patientRequestExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PatientRequestExceptionHandler {
	
	@ExceptionHandler(value = PatientRequestSaveException.class)
	public ResponseEntity<String> patientRequestSaveException(PatientRequestSaveException exception) {
		log.error("PatientRequestNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = PatientRequestIdNotFoundException.class)
	public ResponseEntity<String> patientRequestIdNotFoundException(PatientRequestIdNotFoundException exception) {
		log.error("PatientRequestIdNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = PatientRequestDetailsNotFoundException.class)
	public ResponseEntity<String> patientRequestDeatilsNotFoundException(PatientRequestDetailsNotFoundException exception) {
		log.error("PatientRequestDeatilsNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = PatientRequestDeleteException.class)
	public ResponseEntity<String> patientRequestDeleteException(PatientRequestDeleteException exception) {
		log.error("PatientRequestDeleteException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = PatientRequestUpdateException.class)
	public ResponseEntity<String> patientRequestUpdateException(PatientRequestUpdateException exception) {
		log.error("PatientRequestUpdateException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentException(IllegalArgumentException exception) {
		log.error("IllegalArgumentException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		log.error("Exception-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
