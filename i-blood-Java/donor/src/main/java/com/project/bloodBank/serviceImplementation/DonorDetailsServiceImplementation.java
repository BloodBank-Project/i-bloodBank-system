package com.project.bloodBank.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.constants.CommonConstants;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsDeleteException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsFetchException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsIdNotFoundException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsSaveException;
import com.project.bloodBank.donorDetailsExceptions.DonorDetailsUpdateException;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.repository.DonorDetailsRepository;
import com.project.bloodBank.service.DonorDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonorDetailsServiceImplementation implements DonorDetailsService {

	@Autowired
	private DonorDetailsRepository detailsRepository;

	/**
	 * Saves a donation record in the database.
	 * 
	 * @param donorEntity The donation record to be saved
	 * 
	 * @return The saved donation record
	 */
	@Override
	public DonorDetailsEntity saveDonation(DonorDetailsEntity detailsEntity) throws DonorDetailsSaveException {
		log.info("Saving donation record : {}", detailsEntity);
		try {
			detailsRepository.save(detailsEntity);
			log.info("Donation record saved Successfully");
		} catch (DonorDetailsSaveException e) {
			log.error("Error occurred while saving data: {}", e.getMessage());
			throw e;
		}
		return detailsEntity;
	}

	/**
	 * Retrieves the donor details bean associated with the given donation ID. If
	 * the donation ID is not found, throws a DonationDetailsNotFoundException.
	 * 
	 * @param donationId The ID of the donation to retrieve.
	 * @return The donor details bean associated with the donation ID, or an empty
	 *         bean if not found.
	 */
	@Override
	public DonorDetailsBean getByDonationId(Long donationId) throws DonorDetailsIdNotFoundException {
		Optional<DonorDetailsEntity> donarDetailsId = detailsRepository.findById(donationId);
		log.info("Going to fetch donation Details using ID: {}", donationId);
		DonorDetailsBean bloodGroupBean = new DonorDetailsBean();

		if (donarDetailsId.isEmpty()) {
			try {
				donarDetailsId.orElseThrow(() -> new DonorDetailsIdNotFoundException(
						"donation Details are not found with this ID " + donationId));
			} catch (DonorDetailsIdNotFoundException e) {
				log.error("Error occurred while fetching donation ID: {}", e.getMessage());
				throw e;
			}
		} else {
			Long donationId1 = donarDetailsId.get().getDonationId();
			detailsRepository.getBloodGroupType(donationId1);
			bloodGroupBean = detailsRepository.getBloodGroup(donationId1);
			log.info("Donation Details fetched successfully using ID");
		}
		return bloodGroupBean;
	}

	/**
	 * Updates a donation record.
	 * 
	 * @param donorEntity, The DonorDetailsEntity object representing the updated
	 *                     donation record.
	 */
	@Override
	public void updateDonation(DonorDetailsEntity detailsEntity) throws DonorDetailsUpdateException {
		log.info("Going to update donation record : {}", detailsEntity);
		Optional<DonorDetailsEntity> donationId = detailsRepository.findById(detailsEntity.getDonationId());

		if (donationId.isPresent()) {
			DonorDetailsEntity donorDetails = donationId.get();
			detailsEntity.setDonorId(donorDetails.getDonorId());
			detailsRepository.save(detailsEntity);
			log.info("Donation record Updated Successfully");
		} else {
			try {
				throw new DonorDetailsUpdateException("Donation record is not found for updation");
			} catch (DonorDetailsUpdateException e) {
				log.error("Error occurred while updating data: {}", e.getMessage());
				throw e;
			}
		}
	}

	/**
	 * Deletes a donation record by its ID.
	 * 
	 * @param donationId The ID of the donation record to be deleted.
	 */
	@Override
	public void deleteDonation(Long donationId) throws DonorDetailsDeleteException {
		log.info("Going to delete donation record : {}", donationId);
		Optional<DonorDetailsEntity> donarDonationId = detailsRepository.findById(donationId);
		if (donarDonationId.isEmpty()) {
			try {
				donarDonationId
						.orElseThrow(() -> new DonorDetailsDeleteException("Donation ID is not found for deletion"));
				log.info("Donation record Deleted Successfully");
			} catch (DonorDetailsDeleteException e) {
				log.error("Error occurred while deleting data: {}", e.getMessage());
				throw e;
			}
		} else {
			detailsRepository.deleteById(donationId);
		}
	}

	/**
	 * Retrieves all donation records from the database and converts them into
	 * DonorDetailsBean objects. Logs each step of the process and handles
	 * exceptions gracefully.
	 * 
	 * @return A list of DonorDetailsBean objects representing the donation records.
	 */
	@Override
	public List<DonorDetailsBean> getAllDonations() throws DonorDetailsFetchException {
		log.info("Going to fetch donation records ");
		List<DonorDetailsEntity> donarEntities = detailsRepository.findAll();
		List<DonorDetailsBean> donarBeans = new ArrayList<>();

		donarEntities.forEach(donorDetailsEntity -> {
			try {
				Long donationId = donorDetailsEntity.getDonationId();
				log.info("Donation ID: {}", donationId);

				String bloodGroupRepo = detailsRepository.getBloodGroupType(donationId);
				log.info("Blood Group Repo: {}", bloodGroupRepo);

				DonorDetailsBean bloodGroupBean = detailsRepository.getBloodGroup(donationId);
				donarBeans.add(bloodGroupBean);
				log.info("Blood Group Bean: {}", bloodGroupBean);

				log.info("Donation records fetched successfully");
			} catch (DonorDetailsFetchException e) {
				log.error("Error processing donation ID {}: {}", donorDetailsEntity.getDonationId(), e.getMessage());
				throw e;
			}

		});
		return donarBeans;
	}

	/**
	 * Update the status of a donation to "Accepted" and increment the blood
	 * quantity.
	 * 
	 * @param detailsEntity The donation details entity to update
	 */
	@Override
	public void updateStatusAsAccepted(DonorDetailsEntity detailsEntity) throws DonorDetailsUpdateException {
		log.info("Updating donation status as Accepted...");
		try {
			detailsRepository.updateBloodQuantity(detailsEntity.getDonationId(), detailsEntity.getBloodQuantity());
			Optional<DonorDetailsEntity> optionalDonation = detailsRepository.findById(detailsEntity.getDonationId());
			if (optionalDonation.isPresent() && (detailsEntity.getStatus() != null
					|| detailsEntity.getStatus().equals(CommonConstants.pending))) {
				DonorDetailsEntity donationDetails = optionalDonation.get();
				detailsEntity.setDonorId(donationDetails.getDonorId());
				detailsEntity.setStatus(CommonConstants.accept);
				detailsRepository.save(detailsEntity);

				log.info("Donation status updated to Accepted successfully.");
			} else {
				log.warn(
						"Donation status cannot be updated. Either donation does not exist or its status is not pending.");
			}
		} catch (DonorDetailsUpdateException e) {
			log.error("Error occurred while updating donation status as Accepted: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Update the status of a donation to "Rejected".
	 * 
	 * @param detailsEntity The donation details entity to update
	 */
	@Override
	public void updateStatusAsRejected(DonorDetailsEntity detailsEntity) throws DonorDetailsUpdateException {
		log.info("Updating donation status as Rejected...");
		try {
			Optional<DonorDetailsEntity> optionalDonation = detailsRepository.findById(detailsEntity.getDonationId());
			if (optionalDonation.isPresent() && (detailsEntity.getStatus() != null
					|| detailsEntity.getStatus().equals(CommonConstants.pending))) {
				DonorDetailsEntity donationDetails = optionalDonation.get();
				detailsEntity.setDonorId(donationDetails.getDonorId());
				detailsEntity.setStatus(CommonConstants.reject);
				detailsRepository.save(detailsEntity);

				log.info("Donation status updated to Rejected successfully.");
			} else {
				log.warn(
						"Donation status cannot be updated. Either donation does not exist or its status is not pending.");
			}
		} catch (DonorDetailsUpdateException e) {
			log.error("Error occurred while updating donation status as Rejected: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of donation records.
	 *
	 * @return The total count of donation records
	 */
	@Override
	public Long donationsCount() throws DonorDetailsFetchException {
		log.info("Fetching total count of donation records");
		try {
			Long count = detailsRepository.countDonations();
			log.info("Total count of donation records fetched successfully: {}", count);
			return count;
		} catch (DonorDetailsFetchException e) {
			log.error("Error occurred while fetching total count of donation records: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of donation records with accepted status.
	 *
	 * @return The total count of donation records with accepted status
	 */
	@Override
	public Long totalAcceptedStatus() throws DonorDetailsFetchException {
		log.info("Fetching total count of donation records with accepted status");
		try {
			Long count = detailsRepository.getTotalAcceptedCount();
			log.info("Total count of donation records with accepted status fetched successfully: {}", count);
			return count;
		} catch (DonorDetailsFetchException e) {
			log.error("Error occurred while fetching total count of donation records with accepted status: {}",
					e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of donation records with rejected status.
	 *
	 * @return The total count of donation records with rejected status
	 */
	@Override
	public Long totalRejectedStatus() throws DonorDetailsFetchException {
		log.info("Fetching total count of donation records with rejected status");
		try {
			Long count = detailsRepository.getTotalRejectedCount();
			log.info("Total count of donation records with rejected status fetched successfully: {}", count);
			return count;
		} catch (DonorDetailsFetchException e) {
			log.error("Error occurred while fetching total count of donation records with rejected status: {}",
					e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of donation records with pending status.
	 *
	 * @return The total count of donation records with pending status
	 */
	@Override
	public Long totalPendingStatus() throws DonorDetailsFetchException {
		log.info("Fetching total count of donation records with pending status");
		try {
			Long count = detailsRepository.getTotalPendingCount();
			log.info("Total count of donation records with pending status fetched successfully: {}", count);
			return count;
		} catch (DonorDetailsFetchException e) {
			log.error("Error occurred while fetching total count of donation records with pending status: {}",
					e.getMessage());
			throw e;
		}
	}

	/**
	 * Retrieves the total count of blood banks.
	 *
	 * @return The total count of blood banks
	 */
	@Override
	public Long bloodBanksCount() throws DonorDetailsFetchException {
		log.info("Fetching total count of blood banks");
		try {
			Long count = detailsRepository.getBloodBankCount();
			log.info("Total count of blood banks fetched successfully: {}", count);
			return count;
		} catch (DonorDetailsFetchException e) {
			log.error("Error occurred while fetching total count of blood banks: {}", e.getMessage());
			throw e;
		}
	}
}
