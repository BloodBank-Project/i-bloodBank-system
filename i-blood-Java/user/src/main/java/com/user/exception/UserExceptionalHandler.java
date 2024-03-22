package com.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionalHandler extends RuntimeException {

	private static final long serialVersionUID = 6115968063869056345L;

	private static Logger log = LoggerFactory.getLogger(UserNotFoundException.class.getSimpleName());

	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<String> invalidException(InvalidCredentialsException exception) {

		log.error("InvalidCredentialsException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoUsersFoundException.class)
	public ResponseEntity<String> noUsersFoundException(NoUsersFoundException exception) {

		log.error("NoUsersFoundException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PasswordUpdateException.class)
	public ResponseEntity<String> passwordUpdateException(PasswordUpdateException exception) {

		log.error("PasswordUpdateException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserDeleteException.class)
	public ResponseEntity<String> userDeleteException(UserDeleteException exception) {

		log.error("UserDeleteException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> exception(UserNotFoundException exception) {

		log.error("UserNotFoundException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserSaveException.class)
	public ResponseEntity<String> userSaveException(UserSaveException exception) {

		log.error("UserSaveException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserUpdateException.class)
	public ResponseEntity<String> userUpdateException(UserUpdateException exception) {

		log.error("UserUpdateException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> exception(IllegalArgumentException exception) {

		log.error("IllegalArgumentException - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exception(Exception exception) {

		log.error("Exception - ", exception.getMessage(), exception);

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
