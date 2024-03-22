package com.patient.patientExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice

public class PatientExceptionHandler {
	@ExceptionHandler(value = PatientIdNotFoundException.class)
	public ResponseEntity<String> patientIdNotFoundException(PatientIdNotFoundException exception) {
		log.error("PatientIdNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PatientSaveException.class)
	public ResponseEntity<String> patientSaveException(PatientSaveException exception) {
		log.error("PatientNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PatientDeleteException.class)
	public ResponseEntity<String> patientDeleteException(PatientDeleteException exception) {
		log.error("PatientDeleteException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PatientDetailsNotFoundException.class)
	public ResponseEntity<String> patientDeatilsNotFoundException(PatientDetailsNotFoundException exception) {
		log.error("PatientDeatilsNotFoundException-" + exception.getMessage(), exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PatientUpdateException.class)
	public ResponseEntity<String> patientUpdateException(PatientUpdateException exception) {
		log.error("PatientUpdateException-" + exception.getMessage(), exception);
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
