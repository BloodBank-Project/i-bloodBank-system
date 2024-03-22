package com.project.bloodBank.donorDetailsExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DonorDetailsHandlerException {

	@ExceptionHandler(value = DonorDetailsSaveException.class)
	public ResponseEntity<String> exception(DonorDetailsSaveException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DonorDetailsUpdateException.class)
	public ResponseEntity<String> exception(DonorDetailsUpdateException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DonorDetailsDeleteException.class)
	public ResponseEntity<String> exception(DonorDetailsDeleteException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DonorDetailsIdNotFoundException.class)
	public ResponseEntity<String> exception(DonorDetailsIdNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> exception(IllegalArgumentException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DonorDetailsFetchException.class)
	public ResponseEntity<String> exception(DonorDetailsFetchException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
