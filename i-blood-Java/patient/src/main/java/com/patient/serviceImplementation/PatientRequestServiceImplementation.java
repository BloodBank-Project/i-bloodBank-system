package com.patient.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.PatientRequestBean;
import com.patient.constants.CommonConstants;
import com.patient.entity.PatientRequestEntity;
import com.patient.patientRequestExceptions.PatientRequestDeleteException;
import com.patient.patientRequestExceptions.PatientRequestDetailsNotFoundException;
import com.patient.patientRequestExceptions.PatientRequestIdNotFoundException;
import com.patient.patientRequestExceptions.PatientRequestSaveException;
import com.patient.patientRequestExceptions.PatientRequestUpdateException;
import com.patient.repository.PatientRequestRepository;
import com.patient.service.PatientRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatientRequestServiceImplementation implements PatientRequestService {
	@Autowired
	private PatientRequestRepository requestRepository;

	/**
	 * Saves a PatientRequestEntity.
	 *
	 * @param patientRequest The PatientRequestEntity to be saved.
	 * @return The saved PatientRequestEntity.
	 * @throws PatientRequestSaveException if an error occurs while saving the
	 *                                     patient request.
	 */
	@Override
	public PatientRequestEntity savePatientRequest(PatientRequestEntity patientRequest) {
		log.info("Saving patientRequests record : {}", patientRequest);
		try {
			requestRepository.save(patientRequest);
			log.info("Patient record saved Successfully");
		} catch (PatientRequestSaveException e) {
			log.error("Error occurred while saving data: {}", e.getMessage());
			throw e;
		}
		return patientRequest;
	}

	/**
	 * Deletes a PatientRequestEntity by its ID.
	 *
	 * @param id The ID of the patient request to delete.
	 * @throws PatientRequestDeleteException if the patient request with the given
	 *                                       ID is not found.
	 */
	@Override
	public void deletePatientRequest(Long patientId) {
		log.info("Going to delete patientRequest record : {}", patientId);
		Optional<PatientRequestEntity> optional = requestRepository.findById(patientId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientRequestDeleteException("PatientRequest id is not Found"));
				log.info("PatientRequest record Deleted Successfully");
			} catch (PatientRequestDeleteException e) {
				log.error("Error occurred while deleting data: {}", e.getMessage());
				throw e;
			}
		} else {
			requestRepository.deleteById(patientId);
		}

	}

	/**
	 * Retrieves all PatientRequestBeans, including associated blood group
	 * information.
	 *
	 * @return A list containing all PatientRequestBeans.
	 */
	@Override
	public List<PatientRequestBean> getAllPatientRequest() {
		log.info("Going to Fetch Patient Requets");
		try {
			List<PatientRequestEntity> entities = requestRepository.findAll();
			List<PatientRequestBean> beans = new ArrayList<>();

			entities.forEach(patientRequest -> {
				Long requestId = patientRequest.getPatientRequestId();
				log.info("PatientRequest ID: {}", requestId);

				String bloodGroupRepo = requestRepository.getBloodGroupType(requestId);
				log.info("Blood Group Repo: {}", bloodGroupRepo);

				PatientRequestBean bloodGroupBean = requestRepository.getBloodGroup(requestId);
				log.info("Blood Group Bean: {}", bloodGroupBean);

				beans.add(bloodGroupBean);
			});

			log.info("Feteched Successfully");
			return beans;
		} catch (PatientRequestDetailsNotFoundException e) {
			log.error("An error occurred while fetching patient requests: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Updates a PatientRequestEntity.
	 *
	 * @param patientRequest The PatientRequestEntity containing updated
	 *                       information.
	 * @return An Optional containing the updated PatientRequestEntity if it exists,
	 *         otherwise throws a PatientRequestUpdateException.
	 * @throws PatientRequestUpdateException if no patient request exists for the
	 *                                       update.
	 */
	@Override
	public Optional<PatientRequestEntity> updatePatientRequest(PatientRequestEntity patientRequest) {
		log.info("Going to update PatientRequest record : {}", patientRequest);
		try {
			Optional<PatientRequestEntity> optional = requestRepository.findById(patientRequest.getPatientRequestId());
			if (optional.isPresent()) {
				Long patientId = optional.get().getPatientId();
				patientRequest.setPatientId(patientId);
				requestRepository.save(patientRequest);
				log.info("PatientRequest record Updated Successfully");
				return optional;
			}
			throw new PatientRequestUpdateException("No request is found for updation");
		} catch (PatientRequestUpdateException e) {
			log.error("An error occurred while updating patient request: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves a PatientRequestBean by its ID.
	 *
	 * @param id The ID of the patient request to retrieve.
	 * @return The PatientRequestBean with the specified ID, if found.
	 * @throws PatientRequestIdNotFoundException if the patient request with the
	 *                                           given ID is not found.
	 */
	@Override
	public PatientRequestBean getPatientRequest(Long patientId) {
		Optional<PatientRequestEntity> optional = requestRepository.findById(patientId);
		PatientRequestBean requestBean = new PatientRequestBean();
		log.info("Going to fetch PatientRequest Details using ID: {}", patientId);
		if (optional.isEmpty()) {
			try {

				optional.orElseThrow(() -> new PatientRequestIdNotFoundException("Request is not found"));
			} catch (PatientRequestIdNotFoundException e) {
				log.error("Error occurred while fetching PatientRequest ID: {}", e.getMessage());
				throw e;
			}
		} else {
			Long request = optional.get().getPatientRequestId();
			String bloodGroupRepo = requestRepository.getBloodGroupType(request);
			requestBean = requestRepository.getBloodGroup(request);
			log.info("PatientRequest Details fetched successfully using ID");
		}
		return requestBean;
	}

	/**
	 * Updates the status of a patient request to "Accepted".
	 *
	 * @param requestEntity The PatientRequestEntity containing the request details.
	 */
	@Override
	public void updateStatusAsAccepted(PatientRequestEntity requestEntity) {
		log.info("Updating patientRequest status as Accepted...");
		try {
			requestRepository.updateBloodQuantity(requestEntity.getPatientRequestId(),
					requestEntity.getPatientRequestUnits());
			Optional<PatientRequestEntity> optionalRequest = requestRepository
					.findById(requestEntity.getPatientRequestId());
			if (optionalRequest.isPresent() && (requestEntity.getStatus() != null
					|| requestEntity.getStatus().equals(CommonConstants.pending))) {
				PatientRequestEntity patientRequests = optionalRequest.get();
				requestEntity.setPatientId(patientRequests.getPatientId());
				requestEntity.setStatus(CommonConstants.accept);
				requestRepository.save(requestEntity);

				log.info("PatientRequest status updated to Accepted successfully.");
			} else {
				log.warn(
						"PatientRequest status cannot be updated. Either PatienttRequests does not exist or its status is not pending.");
			}
		} catch (PatientRequestUpdateException e) {
			log.error("Error occurred while updating PatientRequest status as Accepted: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Updates the status of a patient request to "Rejected".
	 *
	 * @param requestEntity The PatientRequestEntity containing the request details.
	 */
	@Override
	public void updateStatusAsRejected(PatientRequestEntity requestEntity) {
		log.info("Updating PatientRequest status as Rejected...");
		try {
			Optional<PatientRequestEntity> optionalRequest = requestRepository
					.findById(requestEntity.getPatientRequestId());
			if (optionalRequest.isPresent() && (requestEntity.getStatus() != null
					|| requestEntity.getStatus().equals(CommonConstants.pending))) {
				PatientRequestEntity patientRequests = optionalRequest.get();
				requestEntity.setPatientId(patientRequests.getPatientId());
				requestEntity.setStatus(CommonConstants.reject);
				requestRepository.save(requestEntity);

				log.info("PatientRequest status updated to Rejected successfully.");
			} else {
				log.warn(
						"PatientRequest status cannot be updated. Either PatientRequests does not exist or its status is not pending.");
			}
		} catch (PatientRequestUpdateException e) {
			log.error("Error occurred while updating PatientRequest status as Rejected: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of patient requests.
	 *
	 * @return The total count of patient requests.
	 */
	@Override
	public Long patientRequestsCount() {
		log.info("Retrieving total count of patient requests");
		try {
			Long count = requestRepository.countpatientRequets();
			log.info("Total count of patient requests retrieved successfully: {}", count);
			return count;
		} catch (PatientRequestDetailsNotFoundException e) {
			log.error("An error occurred while retrieving total count of patient requests: {}", e.getMessage());
			throw e;
		}
	}
}
