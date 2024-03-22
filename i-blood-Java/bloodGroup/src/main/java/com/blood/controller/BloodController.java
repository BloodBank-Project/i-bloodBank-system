package com.blood.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blood.entity.Blood;
import com.blood.exception.BloodServiceException;
import com.blood.exception.NoDataFoundException;
import com.blood.service.BloodService;

@RestController
@RequestMapping("/blood")
@CrossOrigin("**")
public class BloodController {
	private static Logger log = LoggerFactory.getLogger(Blood.class.getSimpleName());

	@Autowired
	private BloodService bloodService;

	/**
	 * Endpoint to save a blood group.
	 * 
	 * This method handles the HTTP POST request to save a blood group in the system.
	 * 
	 * @param blood The blood group details provided as a JSON object in the request body.
	 * @return ResponseEntity<Blood> A response entity indicating the success or failure of the save operation.
	 *         Returns HTTP status 201 (Created) if the blood group is successfully saved,
	 *         or HTTP status 406 (Not Acceptable) if there's an issue during the save process.
	 */
	@PostMapping("/save")
	public ResponseEntity<Blood> save(@RequestBody Blood blood) {
		log.info("Attempting to save Blood Group: {}", blood);
		try {
			bloodService.saveBloodGroup(blood);
			log.info("Blood group saved successfully.");
			return new ResponseEntity<>(blood, HttpStatus.CREATED);
		} catch (BloodServiceException e) {
			log.error("Failed to save blood group: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to retrieve a blood group by ID.
	 * 
	 * This method handles the HTTP GET request to fetch a blood group from the system
	 * based on the provided blood ID.
	 * 
	 * @param bloodId The ID of the blood group to be retrieved.
	 * @return ResponseEntity<Blood> A response entity containing the blood group
	 *         if the operation is successful, or an appropriate error response if the blood group
	 *         with the provided ID is not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Blood> getById(@PathVariable Integer bloodId) {

		log.info("Attempting to get Blood Group with ID: {}", bloodId);
		try {
			Blood blood = bloodService.getByBloodId(bloodId);
			log.info("Successfully retrieved Blood group with ID: {}", bloodId);
			return new ResponseEntity<>(blood, HttpStatus.OK);
		} catch (NoDataFoundException e) {
			log.error("Failed to retrieve Blood group with ID: {}", bloodId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to retrieve all blood groups.
	 * 
	 * This method handles the HTTP GET request to fetch all blood groups from the system.
	 * 
	 * @return ResponseEntity<List<Blood>> A response entity containing a list of all blood groups
	 *         if the operation is successful, or an appropriate error response if there are no blood groups available
	 *         or if there's an issue during the retrieval process.
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<List<Blood>> getAll() {

		log.info("Attempting to get all Blood Groups");
		try {
			List<Blood> bloodGroups = bloodService.getAllBloodGroups();
			log.info("Successfully fetched all Blood Groups");
			return new ResponseEntity<>(bloodGroups, HttpStatus.OK);
		} catch (BloodServiceException e) {
			log.error("Failed to fetch all Blood Groups: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
