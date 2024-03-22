package com.blood.serviceImplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blood.entity.Blood;
import com.blood.exception.BloodServiceException;
import com.blood.exception.NoDataFoundException;
import com.blood.repository.BloodRepository;
import com.blood.service.BloodService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BloodServiceImplementation implements BloodService {

	@Autowired
	private BloodRepository bloodRepository;

	
	/**
	 * Save blood group information.
	 * 
	 * @param blood The Blood object containing information about the blood group to
	 *              be saved.
	 */
	@Override
	public void saveBloodGroup(Blood blood) throws BloodServiceException {
		log.info("Saving blood group: {}", blood.toString());
		try {
			bloodRepository.save(blood);
			log.info("Blood group saved successfully.");
		} catch (BloodServiceException e) {
			log.error("An error occurred while saving blood group: {}", e.getMessage());
			throw e;
		}
	}

	
	/**
	 * Retrieve blood information by its ID.
	 * 
	 * @param id The ID of the blood to retrieve.
	 * @return The Blood object corresponding to the provided ID.
	 * @throws NoDataFoundException if no blood data is found for the provided ID.
	 */
	@Override
	public Blood getByBloodId(Integer bloodId) throws NoDataFoundException {
		log.info("Attempting to retrieve blood with ID: {}", bloodId);
		try {
			Optional<Blood> findById = bloodRepository.findById(bloodId);
			if (findById.isPresent()) {
				log.info("Blood with ID {} fetched successfully.", bloodId);
				return findById.get();
			} else {
				log.error("No blood data found for ID: {}", bloodId);
				throw new NoDataFoundException("No blood data found for ID: " + bloodId);
			}
		} catch (NoDataFoundException e) {
			log.error("An error occurred while fetching blood with ID {}: {}", bloodId, e.getMessage());
			throw e;
		}
	}

	
	/**
	 * Retrieve all blood groups.
	 * 
	 * @return A list containing all blood groups.
	 */
	@Override
	public List<Blood> getAllBloodGroups() throws BloodServiceException {
		log.info("Attempting to retrieve all blood groups.");
		try {
			List<Blood> bloodGroups = bloodRepository.findAll();
			log.info("All blood groups fetched successfully.");
			return bloodGroups;
		} catch (BloodServiceException e) {
			log.error("An error occurred while fetching all blood groups: {}", e.getMessage());
			throw e;
		}
	}
}
