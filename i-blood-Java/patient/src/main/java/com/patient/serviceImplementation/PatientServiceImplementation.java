package com.patient.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.patient.bean.PatientBean;
import com.patient.bean.PatientRequestBean;
import com.patient.bean.UserBean;
import com.patient.entity.PatientEntity;
import com.patient.entity.PatientRequestEntity;
import com.patient.patientExceptions.PatientDeleteException;
import com.patient.patientExceptions.PatientIdNotFoundException;
import com.patient.patientExceptions.PatientUpdateException;
import com.patient.patientRequestExceptions.PatientRequestDetailsNotFoundException;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatientServiceImplementation implements PatientService {
	@Autowired
	private PatientRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Saves a PatientEntity along with its associated PatientRequestEntity details.
	 *
	 * @param patient The PatientEntity to be saved.
	 * @return The saved PatientEntity.
	 */
	@Override
	public PatientEntity savePatient(PatientEntity patient) {
		log.info("Saving patient record : {}", patient);
		if (patient.getPatientRequestDetails() != null) {
			for (PatientRequestEntity request : patient.getPatientRequestDetails()) {
				request.setPatientId(patient.getPatientId());
			}
		}
		return repository.save(patient);
		
	}

	/**
	 * Retrieves a PatientEntity by its patient ID.
	 *
	 * @param id The ID of the patient to retrieve.
	 * @return The PatientEntity with the specified ID, if found.
	 * @throws PatientIdNotFoundException if the patient with the given ID is not
	 *                                    found.
	 */
	@Override
	public PatientEntity getByPatientId(Long patientId) {
		Optional<PatientEntity> optional = repository.findById(patientId);
		log.info("Going to fetch Patient Details using ID: {}", patientId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientIdNotFoundException("Patient not found"));
			} catch (PatientIdNotFoundException e) {
				log.error("Error occurred while fetching patient ID: {}", e.getMessage(), e);
				throw e;
			}
		}
		return optional.get();
	}

	/**
	 * Deletes a PatientEntity by its patient ID.
	 *
	 * @param id The ID of the patient to delete.
	 * @throws PatientDeleteException if the patient with the given ID is not found.
	 */
	@Override
	public void deletePatient(Long patientId) {
		Optional<PatientEntity> optional = repository.findById(patientId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientDeleteException("Patient id is not Found"));
				log.info("Patient record Deleted Successfully");
			} catch (PatientDeleteException e) {
				log.error("Error occurred while deleting data: {}", e.getMessage(), e);
				throw e;
			}
		} else {
			repository.deleteById(patientId);
		}
	}

	/**
	 * Retrieves all PatientEntity objects.
	 *
	 * @return A list containing all PatientEntity objects.
	 */
	@Override
	public List<PatientEntity> getAllPatients() {
		log.info("Going to fetch Patient records");
		try {
		List<PatientEntity> list = repository.findAll();
		log.info("All Patients fetched successfully.");
		return list;
		}catch(PatientRequestDetailsNotFoundException e) {
			log.error("Error fetching all Patients: {}", e.getMessage(), e);
			throw new PatientRequestDetailsNotFoundException("Error fetching all Patients: " + e.getMessage());
		}
	}
	
	
	/**
	 * Updates a PatientEntity.
	 *
	 * @param patient The PatientEntity containing updated information.
	 * @return An Optional containing the updated PatientEntity if it exists,
	 *         otherwise throws a PatientUpdateException.
	 * @throws PatientUpdateException if no patient exists for the update.
	 */
	@Override
	public Optional<PatientEntity> updatePatient(PatientEntity patient) {
		log.info("Going to update Patient record : {}", patient);
		Optional<PatientEntity> optional = repository.findById(patient.getPatientId());
		if (optional.isPresent()) {
			repository.save(patient);
			log.info("Patient record Updated Successfully");
			return optional;
		} else {
			try {
			optional.orElseThrow(() -> new PatientUpdateException("No Patient for Updation"));
			}catch(PatientUpdateException e) {
				log.error("Error occurred while updating data: {}", e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * Retrieves a PatientBean by its associated user ID, including details of
	 * patient requests.
	 *
	 * @param id The ID of the user associated with the patient.
	 * @return The PatientBean with the specified user ID, including patient request
	 *         details.
	 * @throws PatientIdNotFoundException if the patient with the given user ID is
	 *                                    not found.
	 */
	@Override
	public PatientBean getByUserId(Long patientId) {
		Optional<PatientEntity> optional = repository.findById(patientId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientIdNotFoundException("Patient id is not Found"));
			} catch (PatientIdNotFoundException e) {
				throw e;
			}
		}

		PatientEntity patient = optional.get();

		List<PatientRequestEntity> patientRequests = patient.getPatientRequestDetails();

		List<PatientRequestBean> patientRequestBeans = new ArrayList<>();
		
		patientRequests.forEach(patientRequest -> {
		    PatientRequestBean patientRequestBean = PatientRequestBean.builder()
		            .patientRequestId(patientRequest.getPatientRequestId())
		            .patientMedicalCondition(patientRequest.getPatientMedicalCondition())
		            .patientRequestOnDate(patientRequest.getPatientRequestOnDate())
		            .patientRequestUnits(patientRequest.getPatientRequestUnits())
		            .status(patientRequest.getStatus())
		            .build();

		    patientRequestBeans.add(patientRequestBean);
		});


		String Userurl = "http://localhost:8083/user/" + patient.getUserId();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<UserBean> reponseEntity = restTemplate.exchange(Userurl, HttpMethod.GET, httpEntity,
				UserBean.class);

		UserBean userBean = reponseEntity.getBody();
		userBean.getBloodGroupId();

		PatientBean patientbean = PatientBean.builder().patientId(patient.getPatientId()).userDetails(userBean)
				.patientRequestDetails(patientRequestBeans).build();

		return patientbean;
	}
	
	@Override
	public Long findPatientIdByUserId(Long userId) {
		Long id = repository.findIdByUserId(userId);
		return id;
	}


}
