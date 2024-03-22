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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsDeleteException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsFetchException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsIdNotFoundException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsSaveException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsUpdateException;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.serviceImplementation.DonorDetailsServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/details")
@Slf4j
@CrossOrigin("*")
public class DonorDetailsController {

	@Autowired
	private DonorDetailsServiceImplementation detailsServiceImplementation;

	/**
	 * Endpoint to save donation details.
	 * 
	 * This method handles the HTTP POST request to save donation details in the system.
	 * 
	 * @param donorEntity The donor details entity containing the donation details provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorDetailsEntity> A response entity indicating the success or failure of the save operation.
	 *         Returns HTTP status 201 (Created) if the donation details are successfully saved,
	 *         or HTTP status 406 (Not Acceptable) if there's an issue during the save process.
	 */
	@PostMapping("/save")
	public ResponseEntity<DonorDetailsEntity> saveDonation(@RequestBody DonorDetailsEntity donorEntity) {
		log.info("Saving Donation: {}", donorEntity);
		try {
			detailsServiceImplementation.saveDonation(donorEntity);
			log.info("Donation Details Saved Successfully");
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.CREATED);
		} catch (DonorDetailsSaveException e) {
			log.error("Failed to Save Donation", e);
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to fetch donation details by donation ID.
	 * 
	 * This method handles the HTTP GET request to fetch donation details from the system based on the provided donation ID.
	 * 
	 * @param donationId The ID of the donation for which details are to be fetched.
	 * @return ResponseEntity<DonorDetailsBean> A response entity containing the donation details for the specified donation ID
	 *         if the operation is successful, or an appropriate error response if the donation ID is not found.
	 */
	@GetMapping("/get/{donationId}")
	public ResponseEntity<DonorDetailsBean> getByDonationId(@PathVariable Long donationId) {
		log.info("Fetching Donation with ID: {}", donationId);
		try {
			DonorDetailsBean byDonorId = detailsServiceImplementation.getByDonationId(donationId);
			log.info("Donation Found Successfully");
			return new ResponseEntity<DonorDetailsBean>(byDonorId, HttpStatus.OK);
		} catch (DonorDetailsIdNotFoundException e) {
			log.error("Failed to Fetch Donation with ID: {}", donationId, e);
			return new ResponseEntity<DonorDetailsBean>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to update donation details.
	 * 
	 * This method handles the HTTP PUT request to update donation details in the system.
	 * 
	 * @param donorEntity The donor details entity containing the updated donation details provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorDetailsEntity> A response entity indicating the success or failure of the update operation.
	 *         Returns HTTP status 200 (OK) if the donation details are successfully updated,
	 *         or HTTP status 404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/update")
	public ResponseEntity<DonorDetailsEntity> updateDonorDetails(@RequestBody DonorDetailsEntity donorEntity) {
		log.info("Updating Donation: {}", donorEntity);
		try {
			detailsServiceImplementation.updateDonation(donorEntity);
			log.info("Donation Updated Successfully");
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.OK);
		} catch (DonorDetailsUpdateException e) {
			log.error("Failed to Update Donation", e);
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to delete donation details by donation ID.
	 * 
	 * This method handles the HTTP DELETE request to delete donation details from the system based on the provided donation ID.
	 * 
	 * @param donationId The ID of the donation to be deleted.
	 * @return ResponseEntity<DonorDetailsEntity> A response entity indicating the success or failure of the delete operation.
	 *         Returns HTTP status 200 (OK) if the donation details are successfully deleted,
	 *         or HTTP status 404 (Not Found) if there's an issue during the delete process.
	 */
	@DeleteMapping("/delete/{donationId}")
	public ResponseEntity<DonorDetailsEntity> deleteDonorDetails(@PathVariable Long donationId) {
		log.info("Deleting Donation with ID: {}", donationId);
		try {
			detailsServiceImplementation.deleteDonation(donationId);
			log.info("Donation Deleted Successfully");
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.OK);
		} catch (DonorDetailsDeleteException e) {
			log.error("Failed to Delete Donation with ID: {}", donationId, e);
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to fetch all donation details.
	 * 
	 * This method handles the HTTP GET request to fetch all donation details from the system.
	 * 
	 * @return ResponseEntity<List<DonorDetailsBean>> A response entity containing a list of all donation details
	 *         if the operation is successful, or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the donation details are successfully fetched,
	 *         or HTTP status 404 (Not Found) if there are no donations found.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<DonorDetailsBean>> donationsList() {
		log.info("Fetching All Donations");
		try {
			List<DonorDetailsBean> allDonars = detailsServiceImplementation.getAllDonations();
			log.info("Donations Fetched Successfully");
			return new ResponseEntity<List<DonorDetailsBean>>(allDonars, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			log.error("Failed to Fetch All Donations", e);
			return new ResponseEntity<List<DonorDetailsBean>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates the status of a donation to "Accepted".
	 * 
	 * This method handles the HTTP PUT request to update the status of a donation to "Accepted" in the system.
	 * 
	 * @param donorEntity The donor details entity containing the donation details and the updated status provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorDetailsEntity> A response entity indicating the success or failure of the update operation.
	 *         Returns HTTP status 200 (OK) if the donation status is successfully updated to "Accepted",
	 *         or HTTP status 404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/status/accepted")
	public ResponseEntity<DonorDetailsEntity> updateBloodQuantity(@RequestBody DonorDetailsEntity donorEntity) {
		log.info("Updating Donation Status As Accepted");
		try {
			detailsServiceImplementation.updateStatusAsAccepted(donorEntity);
			log.info("Donations Status Updated Successfully");
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.OK);
		} catch (DonorDetailsUpdateException e) {
			log.error("Failed to Update Donations", e);
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates the status of a donation to "Rejected".
	 * 
	 * This method handles the HTTP PUT request to update the status of a donation to "Rejected" in the system.
	 * 
	 * @param donorEntity The donor details entity containing the donation details and the updated status provided as a JSON object in the request body.
	 * @return ResponseEntity<DonorDetailsEntity> A response entity indicating the success or failure of the update operation.
	 *         Returns HTTP status 200 (OK) if the donation status is successfully updated to "Rejected",
	 *         or HTTP status 404 (Not Found) if there's an issue during the update process.
	 */

	@PutMapping("/status/rejected")
	public ResponseEntity<DonorDetailsEntity> statusReject(@RequestBody DonorDetailsEntity donorEntity) {
		log.info("Updating Donation Status As Rejected");
		try {
			detailsServiceImplementation.updateStatusAsRejected(donorEntity);
			log.info("Donations Status Updated Successfully");
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.OK);
		} catch (DonorDetailsUpdateException e) {
			log.error("Failed to Update Donations", e);
			return new ResponseEntity<DonorDetailsEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Retrieves the total count of donations.
	 * 
	 * This method handles the HTTP GET request to retrieve the total count of donations in the system.
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of donations if the operation is successful,
	 *         or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the count is successfully retrieved,
	 *         or HTTP status 500 (Internal Server Error) if there's an unexpected error during the retrieval.
	 */
	@GetMapping("/count/donations")
	public ResponseEntity<Long> getDonationsCount() {
		try {
			Long count = detailsServiceImplementation.donationsCount();
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves the total count of donations with status "Accepted".
	 * 
	 * This method handles the HTTP GET request to retrieve the total count of donations in the system with status "Accepted".
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of donations with status "Accepted" if the operation is successful,
	 *         or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the count is successfully retrieved,
	 *         or HTTP status 500 (Internal Server Error) if there's an unexpected error during the retrieval.
	 */
	@GetMapping("/total/accepted")
	public ResponseEntity<Long> getTotalAcceptedStatus() {
		try {
			Long count = detailsServiceImplementation.totalAcceptedStatus();
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves the total count of donations with status "Rejected".
	 * 
	 * This method handles the HTTP GET request to retrieve the total count of donations in the system with status "Rejected".
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of donations with status "Rejected" if the operation is successful,
	 *         or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the count is successfully retrieved,
	 *         or HTTP status 500 (Internal Server Error) if there's an unexpected error during the retrieval.
	 */
	@GetMapping("/total/rejected")
	public ResponseEntity<Long> getTotalRejectedStatus() {
		try {
			Long count = detailsServiceImplementation.totalRejectedStatus();
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves the total count of donations with status "Pending".
	 * 
	 * This method handles the HTTP GET request to retrieve the total count of donations in the system with status "Pending".
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of donations with status "Pending" if the operation is successful,
	 *         or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the count is successfully retrieved,
	 *         or HTTP status 500 (Internal Server Error) if there's an unexpected error during the retrieval.
	 */
	@GetMapping("/total/pending")
	public ResponseEntity<Long> getTotalPendingStatus() {
		try {
			Long count = detailsServiceImplementation.totalPendingStatus();
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves the total count of blood banks in the system.
	 * 
	 * This method handles the HTTP GET request to retrieve the total count of blood banks in the system.
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of blood banks if the operation is successful,
	 *         or an appropriate error response if there's an issue during the process.
	 *         Returns HTTP status 200 (OK) if the count is successfully retrieved,
	 *         or HTTP status 500 (Internal Server Error) if there's an unexpected error during the retrieval.
	 */
	@GetMapping("/total/bloodBanks")
	public ResponseEntity<Long> getTotalBloodBanks() {
		try {
			Long count = detailsServiceImplementation.bloodBanksCount();
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (DonorDetailsFetchException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
