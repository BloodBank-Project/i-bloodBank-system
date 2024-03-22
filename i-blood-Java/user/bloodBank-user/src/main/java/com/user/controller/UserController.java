package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.bean.UserBean;
import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(User.class.getSimpleName());

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {

		log.info("User Details {}", user);

		userService.save(user);

		ResponseEntity<User> repResponseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);

		return repResponseEntity;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserBean> getById(@PathVariable Long id) {
		log.info("fetching user:{}", id);

		UserBean user = userService.getById(id);

		ResponseEntity<UserBean> responseEntity = new ResponseEntity<UserBean>(user, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<User>> getAll() {

		log.info("fetching users : {}");

		List<User> users = userService.getAll();

		ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping(path = "/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {

		log.info("deleted record");

		userService.delete(id);

		ResponseEntity<User> responseEntity = new ResponseEntity<User>(HttpStatus.OK);

		return responseEntity;

	}

	@PutMapping(path = "/update")
	public ResponseEntity<Optional<User>> update(@RequestBody User user) {

		log.info("updated record");

		Optional<User> updateRecord = userService.update(user);

		ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(updateRecord, HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping(path = "/login")
	public ResponseEntity<Optional<User>> login(String email, String password) {

		Optional<User> optional = userService.login(email, password);

		ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);

		return responseEntity;
	}
	
	@GetMapping(path = "/updatePassword")
	public ResponseEntity<Optional<User>> updatePassword(String email, String password) {

		Optional<User> optional = userService.updatePassword(email, password);

		ResponseEntity<Optional<User>> responseEntity = new ResponseEntity<Optional<User>>(optional, HttpStatus.OK);

		return responseEntity;
	}
}
