package com.patient.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.PatientRequestEntity;
import com.patient.patientRequestExceptions.PatientRequestDeleteException;
import com.patient.patientRequestExceptions.PatientRequestDetailsNotFoundException;
import com.patient.patientRequestExceptions.PatientRequestIdNotFoundException;
import com.patient.patientRequestExceptions.PatientRequestSaveException;
import com.patient.patientRequestExceptions.PatientRequestUpdateException;
import com.patient.service.PatientRequestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/patientRequest")
@CrossOrigin("*")
public class PatientRequestController {
	@Autowired
	private PatientRequestService patientRequestService;

	/**
	 * Endpoint to save patient request details.
	 * 
	 * This method handles the HTTP POST request to save patient request details
	 * into the system.
	 * 
	 * @param patientRequest The patient request details provided as a JSON object
	 *                       in the request body.
	 * @return ResponseEntity<PatientRequestEntity> A response entity containing the
	 *         saved patient request details if the operation is successful, or an
	 *         appropriate error response if there's an issue during the save
	 *         process.
	 */
	@PostMapping("/save")
	public ResponseEntity<PatientRequestEntity> savePatientRequest(@RequestBody PatientRequestEntity patientRequest) {
		log.info("Going to Save Details");
		try {
			patientRequestService.savePatientRequest(patientRequest);
			ResponseEntity<PatientRequestEntity> responseEntity = new ResponseEntity(patientRequest,
					HttpStatus.CREATED);
			log.info("saved Successfully");
			return responseEntity;
		} catch (PatientRequestSaveException e) {
			log.error("Error Occured While Saving");
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to delete patient request by ID.
	 * 
	 * This method handles the HTTP DELETE request to delete a patient request from
	 * the system based on the provided ID.
	 * 
	 * @param id The ID of the patient request to be deleted.
	 * @return ResponseEntity<PatientRequestEntity> A response entity indicating the
	 *         success or failure of the delete operation. Returns HTTP status 200
	 *         (OK) if the patient request is successfully deleted, or HTTP status
	 *         404 (Not Found) if the patient request with the provided ID is not
	 *         found.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PatientRequestEntity> deletePatientRequest(@PathVariable Long id) {
		log.info("Going To Delete Patient");
		try {
			patientRequestService.deletePatientRequest(id);
			ResponseEntity<PatientRequestEntity> responseEntity = new ResponseEntity(HttpStatus.OK);
			log.info("Deleted Successfully");
			return responseEntity;
		} catch (PatientRequestDeleteException e) {
			log.error("Error Occured While Deleting");
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to retrieve all patient requests.
	 * 
	 * This method handles the HTTP GET request to fetch all patient requests from
	 * the system.
	 * 
	 * @return ResponseEntity<List<PatientRequestBean>> A response entity containing
	 *         a list of all patient request details if the operation is successful,
	 *         or an appropriate error response if there are no patient requests
	 *         available or if there's an issue during the retrieval process.
	 */
	@GetMapping(path = "/allPatients")
	public ResponseEntity<List<PatientRequestBean>> getAllPatientRequest() {
		log.info("Going to Fetch Data ");
		try {
			List<PatientRequestBean> list = patientRequestService.getAllPatientRequest();
			ResponseEntity<List<PatientRequestBean>> responseEntity = new ResponseEntity(list, HttpStatus.OK);
			log.info("Fetched Successfully");
			return responseEntity;
		} catch (PatientRequestDetailsNotFoundException e) {
			log.error("Error Ocuured while Fetching");
			return new ResponseEntity<List<PatientRequestBean>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to retrieve patient request details by ID.
	 * 
	 * This method handles the HTTP GET request to fetch patient request details
	 * from the system based on the provided ID.
	 * 
	 * @param id The ID of the patient request to retrieve details for.
	 * @return ResponseEntity<PatientRequestBean> A response entity containing the
	 *         patient request details if the operation is successful, or an
	 *         appropriate error response if the patient request with the provided
	 *         ID is not found.
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<PatientRequestBean> getPatientRequest(@PathVariable Long id) {
		log.info("Going To Get Patient Details");
		try {
			PatientRequestBean patientRequest = patientRequestService.getPatientRequest(id);
			ResponseEntity<PatientRequestBean> patient = new ResponseEntity<>(patientRequest, HttpStatus.OK);
			log.info("Patient Details Fetch Successfully");
			return patient;
		} catch (PatientRequestIdNotFoundException e) {
			log.error("Error occured while Fetching patient");
			return new ResponseEntity<PatientRequestBean>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Endpoint to update patient request details.
	 * 
	 * This method handles the HTTP PUT request to update patient request details in
	 * the system.
	 * 
	 * @param patientRequest The updated patient request details provided as a JSON
	 *                       object in the request body.
	 * @return ResponseEntity<Optional<PatientRequestEntity>> A response entity
	 *         containing the updated patient request details if the operation is
	 *         successful, or an appropriate error response if there's an issue
	 *         during the update process.
	 */
	@PutMapping("/update")
	public ResponseEntity<Optional<PatientRequestEntity>> updatePatientRequest(
			@RequestBody PatientRequestEntity patientRequest) {
		log.info("Going to Update Patient Details");
		try {
			Optional<PatientRequestEntity> updatePatientRequest = patientRequestService
					.updatePatientRequest(patientRequest);
			ResponseEntity<Optional<PatientRequestEntity>> responseEntity = new ResponseEntity<>(updatePatientRequest,
					HttpStatus.OK);
			log.info("Updated Successfully");
			return responseEntity;
		} catch (PatientRequestUpdateException e) {
			log.error("Error Occured While Updating");
			return new ResponseEntity<Optional<PatientRequestEntity>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to update the status of a patient request to accepted.
	 * 
	 * This method handles the HTTP PUT request to update the status of a patient
	 * request to "Accepted" in the system.
	 * 
	 * @param requestEntity The patient request entity containing the updated status
	 *                      provided as a JSON object in the request body.
	 * @return ResponseEntity<PatientRequestEntity> A response entity indicating the
	 *         success or failure of the status update operation. Returns HTTP
	 *         status 200 (OK) if the status is successfully updated, or HTTP status
	 *         404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/status/accepted")
	public ResponseEntity<PatientRequestEntity> updateBloodQuantity(@RequestBody PatientRequestEntity requestEntity) {
		log.info("Updating PatientRequest Status As Accepted");
		try {
			patientRequestService.updateStatusAsAccepted(requestEntity);
			log.info("PatientRequest Status Updated Successfully");
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.OK);
		} catch (PatientRequestUpdateException e) {
			log.error("Failed to Update PatientRequests", e);
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to update the status of a patient request to rejected.
	 * 
	 * This method handles the HTTP PUT request to update the status of a patient
	 * request to "Rejected" in the system.
	 * 
	 * @param requestEntity The patient request entity containing the updated status
	 *                      provided as a JSON object in the request body.
	 * @return ResponseEntity<PatientRequestEntity> A response entity indicating the
	 *         success or failure of the status update operation. Returns HTTP
	 *         status 200 (OK) if the status is successfully updated, or HTTP status
	 *         404 (Not Found) if there's an issue during the update process.
	 */
	@PutMapping("/status/rejected")
	public ResponseEntity<PatientRequestEntity> statusReject(@RequestBody PatientRequestEntity requestEntity) {
		log.info("Updating PatientRequest Status As Rejected");
		try {
			patientRequestService.updateStatusAsRejected(requestEntity);
			log.info("PatientRequests Status Updated Successfully");
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.OK);
		} catch (PatientRequestUpdateException e) {
			log.error("Failed to Update PatientRequests", e);
			return new ResponseEntity<PatientRequestEntity>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Endpoint to retrieve the total count of patient requests.
	 * 
	 * This method handles the HTTP GET request to fetch the total count of patient requests from the system.
	 * 
	 * @return ResponseEntity<Long> A response entity containing the total count of patient requests
	 *         if the operation is successful, or an appropriate error response if there's an issue during the process.
	 */
	@GetMapping("/count/requests")
	public ResponseEntity<Long> patientsCount() {
		log.info("Fetching total count of patient requests");
		try {
			Long count = patientRequestService.patientRequestsCount();
			log.info("Total count of patient requests fetched successfully: {}", count);
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occurred while fetching total count of patient requests: {}", e.getMessage());
			return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}