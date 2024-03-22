package com.project.bloodBank.serviceImplementation;

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

import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.bean.UserBean;
import com.project.bloodBank.donorExceptions.DonorDeleteException;
import com.project.bloodBank.donorExceptions.DonorFetchException;
import com.project.bloodBank.donorExceptions.DonorIdNotFoundException;
import com.project.bloodBank.donorExceptions.DonorSaveException;
import com.project.bloodBank.donorExceptions.DonorUpdateException;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.repository.DonorRepository;
import com.project.bloodBank.service.DonorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonorServiceImplementation implements DonorService {

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Saves a donor record in the database.
	 * 
	 * @param donorEntity The donor record to be saved.
	 * @return The saved donor record.
	 */
	@Override
	public DonorEntity saveDonor(DonorEntity donorEntity) throws DonorSaveException {
		log.info("Saving donor record : {}", donorEntity);
		try {
			donorRepository.save(donorEntity);
			log.info("Donor record saved Successfully");
		} catch (DonorSaveException e) {
			log.error("Error occurred while saving data: {}", e.getMessage(), e);
		}
		return donorEntity;
	}

	/**
	 * Retrieves the donor details associated with the given donor ID. If the donor
	 * ID is not found, throws a DonorNotFoundException.
	 * 
	 * @param donarId The ID of the donor to retrieve.
	 * @return The donor details associated with the donor ID.
	 * @throws DonorNotFoundException If the donor ID is not found.
	 */
	@Override
	public DonorEntity getByDonorId(Long donorId) throws DonorIdNotFoundException {
		Optional<DonorEntity> donorsId = donorRepository.findById(donorId);
		log.info("Going to fetch donor Details using ID: {}", donorId);

		if (donorsId.isEmpty()) {
			try {
				donorsId.orElseThrow(
						() -> new DonorIdNotFoundException("Donor record is not found with this ID" + donorId));
			} catch (DonorIdNotFoundException e) {
				log.error("Error occurred while fetching donor ID: {}", e.getMessage(), e);
			}
		}
		return donorsId.get();
	}

	/**
	 * Updates a donor record.
	 * 
	 * @param donorEntity The DonorEntity object representing the updated donor
	 *                    record.
	 */
	@Override
	public void updateDonor(DonorEntity donorEntity) throws DonorUpdateException {
		log.info("Going to update donor record : {}", donorEntity);
		Optional<DonorEntity> donorId = donorRepository.findById(donorEntity.getId());

		if (donorId.isPresent()) {
			donorRepository.save(donorEntity);
			log.info("Donor record Updated Successfully");
		} else {
			try {
				throw new DonorUpdateException("Donation record is not found for updation ");
			} catch (DonorUpdateException e) {
				log.error("Error occurred while updating data: {}", e.getMessage(), e);
			}
		}

	}

	/**
	 * Deletes a donor record by its ID.
	 * 
	 * @param donarId The ID of the donor record to be deleted.
	 */
	@Override
	public void deleteDonor(Long donorId) throws DonorDeleteException {
		Optional<DonorEntity> donorsid = donorRepository.findById(donorId);
		if (donorsid.isEmpty()) {
			try {
				donorsid.orElseThrow(() -> new DonorDeleteException("Donation ID is not found for deletion"));
				log.info("Donor record Deleted Successfully");
			} catch (DonorDeleteException e) {
				log.error("Error occurred while deleting data: {}", e.getMessage(), e);
			}
		} else {
			donorRepository.deleteById(donorId);
		}

	}

	/**
	 * Retrieves all donor records from the database.
	 * 
	 * @return A list of all donor records.
	 */
	@Override
	public List<DonorEntity> getAllDonors() throws DonorFetchException {
		log.info("Going to fetch donation records");
		try {
			List<DonorEntity> donors = donorRepository.findAll();
			log.info("All donars fetched successfully.");
			return donors;
		} catch (DonorFetchException e) {
			log.error("Error fetching all donors: {}", e.getMessage(), e);
			throw new DonorFetchException("Error fetching all donors: " + e.getMessage());
		}
	}

	/**
	 * Retrieves the donor details associated with the given user ID. If the user ID
	 * is not found, throws a DonorNotFoundException.
	 * 
	 * @param userId The ID of the user to retrieve donor details.
	 * @return A DonorBean object representing the donor details associated with the
	 *         user ID.
	 * @throws DonorNotFoundException If the user ID is not found.
	 */
	@Override
	public DonorBean getByUserId(Long userId) throws DonorIdNotFoundException {
		try {
			Optional<DonorEntity> usersId = donorRepository.findById(userId);
			if (usersId.isEmpty()) {
				try {
					usersId.orElseThrow(() -> new DonorIdNotFoundException("Donor Id Is Not Found"));
				} catch (DonorIdNotFoundException e) {
					throw e;
				}
			}
			DonorEntity donorEntity = usersId.get();

			List<DonorDetailsEntity> donorEntities = donorEntity.getDonarDetails();
			List<DonorDetailsBean> donorBeans = new ArrayList<>();

			donorEntities.forEach(donorDetailsEntity -> {
				DonorDetailsBean donorDetailsBean = DonorDetailsBean.builder()
						.donationId(donorDetailsEntity.getDonationId())
						.bloodQuantity(donorDetailsEntity.getBloodQuantity())
						.dateOfDonation(donorDetailsEntity.getDateOfDonation()).status(donorDetailsEntity.getStatus())
						.build();

				donorBeans.add(donorDetailsBean);
			});

			String Userurl = "http://localhost:8083/user/" + userId;
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			headers.setContentType(MediaType.APPLICATION_JSON);

			ResponseEntity<UserBean> reponseEntity = restTemplate.exchange(Userurl, HttpMethod.GET, httpEntity,
					UserBean.class);

			UserBean userBean = reponseEntity.getBody();
			userBean.getBloodGroupId();
			userBean.getBloodBankId();

			DonorBean donorBean1 = DonorBean.builder().id(donorEntity.getId()).userId(userBean).donarDetails(donorBeans)
					.build();

			log.info("Donor details fetched successfully for user ID: {}", userId);
			return donorBean1;
		} catch (DonorIdNotFoundException e) {
			log.error("Error fetching donor details for user ID {}: {}", userId, e.getMessage(), e);
			throw new DonorIdNotFoundException(
					"Error fetching donor details for user ID " + userId + ": " + e.getMessage());
		}
	}
	
	@Override
	public Long findDonorIdByUserId(Long userId) {
		  Long id = donorRepository.findIdByUserId(userId);
		return id;
	}


}
