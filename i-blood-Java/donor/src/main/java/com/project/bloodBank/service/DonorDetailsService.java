package com.project.bloodBank.service;

import java.util.List;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.entity.DonorDetailsEntity;

public interface DonorDetailsService {

	DonorDetailsEntity saveDonation(DonorDetailsEntity detailsEntity) ;

	DonorDetailsBean getByDonationId(Long donationId);

	void updateDonation(DonorDetailsEntity detailsEntity);

	void deleteDonation(Long donationId);

	List<DonorDetailsBean> getAllDonations();

	void updateStatusAsAccepted(DonorDetailsEntity detailsEntity);

	void updateStatusAsRejected(DonorDetailsEntity detailsEntity);

	Long donationsCount();

	Long totalAcceptedStatus();

	Long totalRejectedStatus();

	Long totalPendingStatus();

	Long bloodBanksCount();
}
