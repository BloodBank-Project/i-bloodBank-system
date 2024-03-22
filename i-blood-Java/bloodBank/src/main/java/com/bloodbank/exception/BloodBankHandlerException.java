package com.bloodbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BloodBankHandlerException {

	@ExceptionHandler(value = BloodBankSaveException.class)
	public ResponseEntity<String> exception(BloodBankSaveException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BloodBankDeleteException.class)
	public ResponseEntity<String> exception(BloodBankDeleteException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BloodBankUpdateException.class)
	public ResponseEntity<String> exception(BloodBankUpdateException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BloodBankFetchException.class)
	public ResponseEntity<String> exception(BloodBankFetchException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<String> exception(IdNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> exception(IllegalArgumentException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}

}
