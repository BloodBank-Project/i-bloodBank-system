package com.bloodbank.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodbank.entity.BloodBank;
import com.bloodbank.exception.BloodBankFetchException;
import com.bloodbank.exception.BloodBankSaveException;
import com.bloodbank.exception.BloodBankUpdateException;
import com.bloodbank.exception.IdNotFoundException;
import com.bloodbank.repository.BloodBankRepository;
import com.bloodbank.service.BloodBankService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BloodBankServiceImpl implements BloodBankService {
	
	@Autowired
	private BloodBankRepository bloodBankRepository;

	/**
	 * Saves a blood bank entity.
	 *
	 * @param bloodBank The BloodBank object to be saved.
	 */
	@Override
	public void saveBloodBank(BloodBank bloodbank) throws BloodBankSaveException {
		log.info("Attempting to save Blood Bank.");

		try {
			bloodBankRepository.save(bloodbank);
			log.info("Blood Bank saved successfully.");
		} catch (BloodBankSaveException e) {
			log.error("An error occurred while saving Blood Bank: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves a blood bank entity by its ID.
	 *
	 * @param bankId The ID of the blood bank to retrieve.
	 * @return The BloodBank object corresponding to the provided ID.
	 * @throws IdNotFoundException if the ID is not found.
	 */
	@Override
	public BloodBank getByBankId(Long bankId) throws IdNotFoundException {
		log.info("Attempting to retrieve Blood Bank by ID: {}", bankId);

		Optional<BloodBank> bloodBankId = bloodBankRepository.findById(bankId);
		if (bloodBankId.isEmpty()) {
			try {
				bloodBankId.orElseThrow(() -> new IdNotFoundException("ID not found: " + bankId));
			} catch (IdNotFoundException e) {
				throw e;
			}
		}
		return bloodBankId.get();
	}

	/**
	 * Deletes a blood bank entity by its ID.
	 *
	 * @param bankId The ID of the blood bank to delete.
	 * @throws IdNotFoundException if the ID is not found.
	 */
	@Override
	public void deleteBloodBank(Long bankId) throws IdNotFoundException {
		log.info("Attempting to delete Blood Bank with ID: {}", bankId);

		Optional<BloodBank> bloodBank = bloodBankRepository.findById(bankId);
		if (bloodBank.isPresent()) {
			bloodBankRepository.deleteById(bankId);
			log.info("Blood Bank with ID {} deleted successfully.", bankId);
		} else {
			bloodBank.orElseThrow(() -> new IdNotFoundException("ID not found: " + bankId));
		}
	}

	/**
	 * Retrieves all blood banks.
	 *
	 * @return A list containing all blood banks.
	 * @throws BloodBankFetchException if an error occurs while fetching blood
	 *                                 banks.
	 */
	@Override
	public List<BloodBank> getAllBloodBanks() throws BloodBankFetchException {
		log.info("Attempting to retrieve all Blood Banks.");

		try {
			List<BloodBank> bloodBanks = bloodBankRepository.findAll();
			log.info("Successfully fetched all Blood Banks.");
			return bloodBanks;
		} catch (BloodBankFetchException e) {
			log.error("An error occurred while fetching all Blood Banks: {}", e.getMessage());
			throw new BloodBankFetchException("Error occurred while fetching all Blood Banks", e);
		}
	}

	/**
	 * Updates a blood bank entity.
	 *
	 * @param bloodBank The BloodBank object to be updated.
	 * @throws BloodBankUpdateException if an error occurs while updating the blood
	 *                                  bank.
	 */
	@Override
	public void updateBloodBank(BloodBank bloodBank) throws BloodBankUpdateException {
		log.info("Attempting to update Blood Bank with ID: {}", bloodBank.getBloodBankId());

		try {
			Optional<BloodBank> bloodBankDetails = bloodBankRepository.findById(bloodBank.getBloodBankId());
			if (bloodBankDetails.isPresent()) {
				bloodBankRepository.save(bloodBank);
				log.info("Blood Bank updated successfully.");
			} else {
				bloodBankDetails
						.orElseThrow(() -> new IdNotFoundException("ID not found: " + bloodBank.getBloodBankId()));
			}
		} catch (BloodBankUpdateException e) {
			log.error("An error occurred while updating Blood Bank: {}", e.getMessage());
			throw new BloodBankUpdateException("Error occurred while updating Blood Bank", e);
		}
	}

	/**
	 * Retrieves blood banks by location.
	 *
	 * @param location The location to search for.
	 * @return A list containing blood banks located at the specified location.
	 * @throws BloodBankFetchException if an error occurs while fetching blood banks
	 *                                 by location.
	 */
	@Override
	public List<BloodBank> findByBloodBankLocation(String location) throws BloodBankFetchException {
		log.info("Attempting to retrieve Blood Banks by location: {}", location);

		try {
			List<BloodBank> locations = bloodBankRepository.findByLocation(location);
			log.info("Successfully fetched Blood Banks by location: {}", location);
			return locations;
		} catch (BloodBankFetchException e) {
			log.error("An error occurred while fetching Blood Banks by location {}: {}", location, e.getMessage());
			throw new BloodBankFetchException("Error occurred while fetching Blood Banks by location", e);
		}
	}
}
