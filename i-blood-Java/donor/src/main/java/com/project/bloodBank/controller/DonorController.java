package com.project.bloodBank.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.donorExceptions.DonorDeleteException;
import com.project.bloodBank.donorExceptions.DonorFetchException;
import com.project.bloodBank.donorExceptions.DonorIdNotFoundException;
import com.project.bloodBank.donorExceptions.DonorSaveException;
import com.project.bloodBank.donorExceptions.DonorUpdateException;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.serviceImplementation.DonorServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/donor")
@Slf4j
@CrossOrigin("*")
public class DonorController {

	@Autowired
	private DonorServiceImplementation donorServiceImplementation;

	/**
	 * Endpoint to save a donor record.
	 * 
	 * This method handles the HTTP POST request to save a donor record in the system.
	 * 
	 * @param donorEntity The donor entity containing the donor record details provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorEntity> A response entity indicating the success or failure of the save operation.
	 *         Returns HTTP status 201 (Created) if the donor record is successfully saved,
	 *         or HTTP status 406 (Not Acceptable) if there's an issue during the save process.
	 */
	@PostMapping("/save")
	public ResponseEntity<DonorEntity> saveDonorRecord(@RequestBody DonorEntity donorEntity) {
		log.info("Saving Donor");
		try {
			DonorEntity donor = donorServiceImplementation.saveDonor(donorEntity);
			log.info("Donor Saved Successfully");
			return new ResponseEntity<DonorEntity>(donor, HttpStatus.CREATED);
		} catch (DonorSaveException e) {
			log.error("Saving of Donor Unsuccessful");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to retrieve a donor record by ID.
	 * 
	 * This method handles the HTTP GET request to fetch a donor record from the system
	 * based on the provided donor ID.
	 * 
	 * @param donorId The ID of the donor record to be retrieved.
	 * @return ResponseEntity<DonorEntity> A response entity containing the donor record
	 *         if the operation is successful, or an appropriate error response if the donor record
	 *         with the provided ID is not found.
	 */
	@GetMapping("/get/{donorId}")
	public ResponseEntity<DonorEntity> getByDonorId(@PathVariable Long donorId) {
		log.info("Getting Donor by Id");
		try {
			DonorEntity byDonorId = donorServiceImplementation.getByDonorId(donorId);
			log.info("Donor with Id {} found", donorId);
			return new ResponseEntity<DonorEntity>(byDonorId, HttpStatus.OK);
		} catch (DonorIdNotFoundException e) {
			log.error("Donor with Id {} not found", donorId);
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to update a donor record.
	 * 
	 * This method handles the HTTP PUT request to update a donor record in the system.
	 * 
	 * @param donorEntity The donor entity containing the updated donor record details provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorEntity> A response entity indicating the success or failure of the update operation.
	 *         Returns HTTP status 200 (OK) if the donor record is successfully updated,
	 *         or HTTP status 404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/update")
	public ResponseEntity<DonorEntity> updateDonorRecord(@RequestBody DonorEntity donorEntity) {
		log.info("Updating Donor");
		try {
			donorServiceImplementation.updateDonor(donorEntity);
			log.info("Donor Updated Successfully");
			return new ResponseEntity<DonorEntity>(HttpStatus.OK);
		} catch (DonorUpdateException e) {
			log.error("Updating of Donor Unsuccessful");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to delete a donor record.
	 * 
	 * This method handles the HTTP DELETE request to delete a donor record from the system.
	 * 
	 * @param donorId The ID of the donor record to be deleted.
	 * @return ResponseEntity<DonorEntity> A response entity indicating the success or failure of the delete operation.
	 *         Returns HTTP status 200 (OK) if the donor record is successfully deleted,
	 *         or HTTP status 404 (Not Found) if there's an issue during the delete process.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DonorEntity> deleteDonorRecord(@PathVariable Long donorId) {
		log.info("Deleting Donor");
		try {
			donorServiceImplementation.deleteDonor(donorId);
			log.info("Donor Deleted Successfully");
			return new ResponseEntity<DonorEntity>(HttpStatus.OK);
		} catch (DonorDeleteException e) {
			log.error("Deleting of Donar Unsuccessful");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to fetch all donors.
	 * 
	 * This method handles the HTTP GET request to fetch all donors from the system.
	 * 
	 * @return ResponseEntity<List<DonorEntity>> A response entity containing a list of all donors
	 *         if the operation is successful, or an appropriate error response if there are no donors available
	 *         or if there's an issue during the retrieval process.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<DonorEntity>> donorsList() {
		log.info("Fetching all Donors");
		try {
			List<DonorEntity> allDonors = donorServiceImplementation.getAllDonors();
			log.info("Donors Fetched Successfully");
			return new ResponseEntity<List<DonorEntity>>(allDonors, HttpStatus.OK);
		} catch (DonorFetchException e) {
			log.error("Fetching of Donors Unsuccessful");
			return new ResponseEntity<List<DonorEntity>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to fetch user details by user ID.
	 * 
	 * This method handles the HTTP GET request to fetch donor details from the system based on the provided user ID.
	 * 
	 * @param userId The ID of the user for whom donor details are to be fetched.
	 * @return ResponseEntity<DonorBean> A response entity containing the donor details for the specified user ID
	 *         if the operation is successful, or an appropriate error response if the user ID is not found.
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<DonorBean> getUserDetails(@PathVariable Long userId) {

		log.info("Fetching User Details for Id {}", userId);
		try {
			DonorBean donorBean = donorServiceImplementation.getByUserId(userId);
			log.info("User Details Fetched Successfully");
			return new ResponseEntity<DonorBean>(donorBean, HttpStatus.OK);
		} catch (DonorIdNotFoundException e) {
			log.error("Fetching User Details Unsuccessful");
			return new ResponseEntity<DonorBean>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to find donor ID by user ID.
	 * 
	 * This method handles the HTTP GET request to find the donor ID associated with the provided user ID.
	 * 
	 * @param id The user ID for whom the associated donor ID is to be found.
	 * @return ResponseEntity<Long> A response entity containing the donor ID associated with the specified user ID
	 *         if the operation is successful, or an appropriate error response if there's an issue during the process.
	 */
	@GetMapping(path = "/userId/{id}")
	public ResponseEntity<Long> findByUserId(@PathVariable("id") Long id) {
		log.info("Finding Donor ID by User ID: {}", id);
		try {
			Long donorId = donorServiceImplementation.findDonorIdByUserId(id);
			log.info("Donor ID Found Successfully for User ID: {}", id);
			return new ResponseEntity<Long>(donorId, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occurred while finding Donor ID by User ID: {}", id);
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
