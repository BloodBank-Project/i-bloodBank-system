package com.bloodbank.controller;

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

import com.bloodbank.entity.BloodBank;
import com.bloodbank.exception.IdNotFoundException;
import com.bloodbank.service.BloodBankService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/bloodbank")
@CrossOrigin("*")
public class BloodBankController{

	@Autowired
	private BloodBankService bloodBankService;

	/**
	 * Endpoint to save blood bank details.
	 * 
	 * This method handles the HTTP POST request to save blood bank details into the system.
	 * 
	 * @param bloodBank The blood bank details provided as a JSON object in the request body.
	 * @return ResponseEntity<BloodBank> A response entity indicating the success or failure of the save operation.
	 *         Returns HTTP status 201 (Created) if the blood bank details are successfully saved,
	 *         or HTTP status 406 (Not Acceptable) if there's an issue during the save process.
	 */
	@PostMapping("/save")
	public ResponseEntity<BloodBank> save(@RequestBody BloodBank bloodBank) {
		log.info("Saving Blood Bank Details: {}", bloodBank);
		try {
			bloodBankService.saveBloodBank(bloodBank);
			log.info("Blood Bank Details Saved Successfully");
			return new ResponseEntity<>(bloodBank, HttpStatus.CREATED);
		} catch (IdNotFoundException e) {
			log.error("Failed to Save Blood Bank Details");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * Endpoint to retrieve blood bank details by ID.
	 * 
	 * This method handles the HTTP GET request to fetch blood bank details from the system
	 * based on the provided bank ID.
	 * 
	 * @param bankId The ID of the blood bank for which details are to be fetched.
	 * @return ResponseEntity<BloodBank> A response entity containing the blood bank details
	 *         if the operation is successful, or an appropriate error response if the blood bank
	 *         with the provided ID is not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BloodBank> getById(@PathVariable Long bankId) {
		log.info("Fetching Blood Bank by ID: {}", bankId);
		try {
			BloodBank bloodBank = bloodBankService.getByBankId(bankId);
			log.info("Blood Bank Details Fetched Successfully by ID: {}", bankId);
			return new ResponseEntity<>(bloodBank, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			log.error("Failed to Fetch Blood Bank Details by ID: {}", bankId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to retrieve all blood bank details.
	 * 
	 * This method handles the HTTP GET request to fetch all blood bank details from the system.
	 * 
	 * @return ResponseEntity<List<BloodBank>> A response entity containing a list of all blood bank details
	 *         if the operation is successful, or an appropriate error response if there are no blood banks available
	 *         or if there's an issue during the retrieval process.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<BloodBank>> getAll() {
		log.info("Fetching All Blood Banks");
		try {
			List<BloodBank> banks = bloodBankService.getAllBloodBanks();
			log.info("All Blood Bank Details Fetched Successfully");
			return new ResponseEntity<>(banks, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			log.error("Failed to Fetch All Blood Bank Details");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to delete blood bank details by ID.
	 * 
	 * This method handles the HTTP DELETE request to delete blood bank details from the system
	 * based on the provided bank ID.
	 * 
	 * @param bankId The ID of the blood bank to be deleted.
	 * @return ResponseEntity<BloodBank> A response entity indicating the success or failure of the delete operation.
	 *         Returns HTTP status 200 (OK) if the blood bank details are successfully deleted,
	 *         or HTTP status 404 (Not Found) if the blood bank with the provided ID is not found.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BloodBank> delete(@PathVariable Long bankId) {
		log.info("Deleting Blood Bank Details with ID: {}", bankId);
		try {
			bloodBankService.deleteBloodBank(bankId);
			log.info("Blood Bank Details Deleted Successfully with ID: {}", bankId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			log.error("Failed to Delete Blood Bank Details with ID: {}", bankId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to update blood bank details.
	 * 
	 * This method handles the HTTP PUT request to update blood bank details in the system.
	 * 
	 * @param bloodBank The updated blood bank details provided as a JSON object in the request body.
	 * @return ResponseEntity<BloodBank> A response entity indicating the success or failure of the update operation.
	 *         Returns HTTP status 200 (OK) if the blood bank details are successfully updated,
	 *         or HTTP status 404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/update")
	public ResponseEntity<BloodBank> update(@RequestBody BloodBank bloodBank) {
		log.info("Updating Blood Bank Details");
		try {
			bloodBankService.updateBloodBank(bloodBank);
			log.info("Blood Bank Details Updated Successfully");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			log.error("Failed to Update Blood Bank Details");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to retrieve blood banks by location.
	 * 
	 * This method handles the HTTP GET request to fetch blood banks from the system
	 * based on the provided location.
	 * 
	 * @param location The location for which blood banks are to be retrieved.
	 * @return ResponseEntity<List<BloodBank>> A response entity containing a list of blood banks
	 *         if the operation is successful, or an appropriate error response if there are no blood banks available
	 *         for the provided location or if there's an issue during the retrieval process.
	 */
	@GetMapping("/location/{location}")
	public ResponseEntity<List<BloodBank>> findByLocation(@PathVariable String location) {
		log.info("Finding Blood Banks by Location: {}", location);
		try {
			List<BloodBank> list = bloodBankService.findByBloodBankLocation(location);
			log.info("Blood Banks Fetched Successfully by Location: {}", location);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			log.error("Failed to Fetch Blood Banks by Location: {}", location);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
