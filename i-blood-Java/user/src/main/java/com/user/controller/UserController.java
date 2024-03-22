package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.bean.UserBean;
import com.user.entity.EmailRequest;
import com.user.entity.User;
import com.user.exception.InvalidCredentialsException;
import com.user.exception.NoUsersFoundException;
import com.user.exception.PasswordUpdateException;
import com.user.exception.UserDeleteException;
import com.user.exception.UserNotFoundException;
import com.user.exception.UserSaveException;
import com.user.exception.UserUpdateException;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin("**")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Endpoint to save a new user.
	 * 
	 * @param user The User object containing the details of the user to be saved.
	 * @return ResponseEntity with HTTP status 201 (Created) and the saved User
	 *         object in the body if the user is saved successfully, or
	 *         ResponseEntity with HTTP status 406 (Not Acceptable) if there's an
	 *         error saving the user.
	 */
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		log.info("User Details {}", user);
		try {
			User save = userService.saveUser(user);
			ResponseEntity<User> repResponseEntity = new ResponseEntity<>(save, HttpStatus.CREATED);
			log.info("saved Successfully");
			return repResponseEntity;
		} catch (UserSaveException e) {
			log.error("saved UnSuccessfully");
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Retrieves a user by ID.
	 * 
	 * @param id The ID of the user to retrieve.
	 * @return ResponseEntity with HTTP status 200 (OK) and the retrieved UserBean
	 *         object in the body if the user is found, or ResponseEntity with HTTP
	 *         status 404 (Not Found) if the user is not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserBean> getByUserId(@PathVariable Long id) {
		log.info("fetching user:{}", id);
		try {
			UserBean user = userService.getByUserId(id);
			ResponseEntity<UserBean> responseEntity = new ResponseEntity<UserBean>(user, HttpStatus.OK);
			log.info("Details Got Successfully");
			return responseEntity;
		} catch (UserNotFoundException e) {
			log.error("Details got UnSuccessfull");
			return new ResponseEntity<UserBean>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Retrieves all users.
	 * 
	 * @return ResponseEntity with HTTP status 200 (OK) and a list of all users in
	 *         the body if users are found, or ResponseEntity with HTTP status 404
	 *         (Not Found) if no users are found.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("fetching users : {}");
		try {
			List<User> users = userService.getAllUsers();
			ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
			log.info("fetched Successfully");
			return responseEntity;
		} catch (NoUsersFoundException e) {
			log.error("fetched UnSuccessfully");
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes a user by ID.
	 * 
	 * @param id The ID of the user to delete.
	 * @return ResponseEntity with HTTP status 200 (OK) if the user is deleted
	 *         successfully, or ResponseEntity with HTTP status 406 (Not Acceptable)
	 *         if there's an error deleting the user.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
		log.info("deleted record");
		try {
			userService.deleteUserById(id);
			ResponseEntity<User> responseEntity = new ResponseEntity<User>(HttpStatus.OK);
			log.info("deleted Successsfully");
			return responseEntity;
		} catch (UserDeleteException e) {
			log.error("Deleted Unsuucessfully");
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * Updates a user record.
	 * 
	 * @param user The User object containing the updated user details.
	 * @return ResponseEntity with HTTP status 200 (OK) and an Optional containing
	 *         the previously stored user object in the body if the user is updated
	 *         successfully, or ResponseEntity with HTTP status 406 (Not Acceptable)
	 *         if there's an error updating the user.
	 */
	@PutMapping("/update")
	public ResponseEntity<Optional<User>> updateUser(@RequestBody User user) {
		log.info("updated record");
		try {
			Optional<User> updateRecord = userService.updateUser(user);
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(updateRecord,
					HttpStatus.OK);
			log.info("updated Successfully");
			return responseEntity;
		} catch (UserUpdateException e) {
			log.error("not updated");
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint for user login authentication.
	 * 
	 * @param user The User object containing the email and password for login
	 *             authentication.
	 * @return ResponseEntity with HTTP status 200 (OK) and an Optional containing
	 *         the user object if the login authentication is successful, or
	 *         ResponseEntity with HTTP status 406 (Not Acceptable) if there's an
	 *         error during login authentication.
	 */
	@PutMapping("/login")
	public ResponseEntity<Optional<User>> userLogin(@RequestBody User user) {

		String email = user.getEmail();
		String password = user.getPassword();

		log.info("Going to Login");
		try {
			Optional<User> optional = userService.userLogin(email, password);
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);
			log.info("loggged in Successfully");
			return responseEntity;
		} catch (InvalidCredentialsException e) {
			log.error("error occured while login");
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to update user password.
	 * 
	 * @param user The User object containing the email and new password to update.
	 * @return ResponseEntity with HTTP status 200 (OK) and an Optional containing
	 *         the updated user object if the password is updated successfully, or
	 *         ResponseEntity with HTTP status 406 (Not Acceptable) if there's an
	 *         error updating the password.
	 */
	@PutMapping("/updatePassword")
	public ResponseEntity<Optional<User>> updateUserPassword(@RequestBody User user) {

		String email = user.getEmail();
		String password = user.getPassword();
		log.info(" going to update password ");
		try {
			Optional<User> optional = userService.updateUserPassword(email, password);
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);
			log.info("Succcessfully updated");
			return responseEntity;
		} catch (PasswordUpdateException e) {
			log.error("Password is not updated");
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to update user location (blood bank ID).
	 * 
	 * @param user The User object containing the email and blood bank ID to update.
	 * @return ResponseEntity with HTTP status 200 (OK) and an Optional containing
	 *         the updated user object if the location is updated successfully, or
	 *         ResponseEntity with HTTP status 406 (Not Acceptable) if there's an
	 *         error updating the location.
	 */
	@PutMapping("/updateLocation")
	public ResponseEntity<Optional<User>> updateUserLocation(@RequestBody User user) {

		String email = user.getEmail();
		Long bloodBankId = user.getBloodBankId();
		log.info(" going to update password ");
		try {
			Optional<User> optional = userService.updateUserBloodBankId(email, bloodBankId);
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);
			log.info("Succcessfully updated");
			return responseEntity;
		} catch (PasswordUpdateException e) {
			log.error("Password is not updated");
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to update user location (blood bank ID) and blood group ID.
	 * 
	 * @param user The User object containing the email, blood bank ID, and blood
	 *             group ID to update.
	 * @return ResponseEntity with HTTP status 200 (OK) and an Optional containing
	 *         the updated user object if the location and blood group are updated
	 *         successfully, or ResponseEntity with HTTP status 406 (Not Acceptable)
	 *         if there's an error updating the location and blood group.
	 */
	@PutMapping("/updateLocationAndGroup")
	public ResponseEntity<Optional<User>> updateUserLocationAndGroup(@RequestBody User user) {

		String email = user.getEmail();
		Long bloodBankId = user.getBloodBankId();
		Long bloodGroupId = user.getBloodGroupId();

		log.info(" going to update password ");
		try {
			Optional<User> optional = userService.updateUserBloodBankIdBlood(email, bloodBankId, bloodGroupId);
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);
			log.info("Succcessfully updated");
			return responseEntity;
		} catch (PasswordUpdateException e) {
			log.error("Password is not updated");
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to send a password reset email.
	 * 
	 * @param emailRequest The EmailRequest object containing the email address to
	 *                     which the reset email will be sent.
	 * @return ResponseEntity with HTTP status 200 (OK) and the EmailRequest object
	 *         in the body if the reset email is sent successfully.
	 */
	@PostMapping("/send-reset-email")
	public ResponseEntity<EmailRequest> sendResetEmail(@RequestBody EmailRequest emailRequest) {
		System.out.println(emailRequest.getEmail());
		userService.sendPasswordResetEmail(emailRequest.getEmail());
		return new ResponseEntity<EmailRequest>(emailRequest, HttpStatus.OK);
	}

	/**
	 * Endpoint to generate OTP and send it via email.
	 * 
	 * @param email The email address for which OTP will be generated and sent.
	 * @return ResponseEntity with HTTP status 200 (OK) and the User object in the
	 *         body if the OTP is generated and sent successfully, or ResponseEntity
	 *         with HTTP status 401 (Unauthorized) if the user is not authorized, or
	 *         ResponseEntity with HTTP status 404 (Not Found) if the email address
	 *         is not found.
	 */
	@GetMapping("/generateOtp")
	public ResponseEntity<User> generateOtpAndSendEmail(@RequestParam String email) {
		System.out.println("generate otp");
		try {
			log.info("Generate otp by using email");
			User user = userService.forgetPassword(email);
			if (user != null) {
				log.info("Generate otp by using email is done");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {

				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {

			log.error("email id is not valid");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * Endpoint to verify OTP.
	 * 
	 * @param email      The email address for which OTP needs to be verified.
	 * @param enteredOtp The OTP entered by the user for verification.
	 * @return ResponseEntity with HTTP status 200 (OK) and a success message in the
	 *         body if the OTP is verified successfully, or ResponseEntity with HTTP
	 *         status 400 (Bad Request) and an error message in the body if the OTP
	 *         is invalid or verification fails.
	 */
	@PostMapping("/verify")
	public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String enteredOtp) {
		try {
			log.info("verify the otp by using email");
			if (userService.verifyOtp(email, enteredOtp)) {
				String jsonString = "{\"message\":\"Verified Successfully\"}";
				log.info("verify the  otp by using email is done");

				return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(jsonString);
			} else {
				log.info("Sending  the invalid otp");
				String jsonString = "{message:Invalid OTP}";
				System.out.println("jsonString" + jsonString);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
						.body(jsonString);
			}
		} catch (Exception e) {
			String jsonString = "{\"message\":\"wrong otp\"}";
			log.error("error handled");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
					.body(jsonString);

		}
	}
}