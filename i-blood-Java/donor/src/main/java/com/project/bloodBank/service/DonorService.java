package com.project.bloodBank.service;

import java.util.List;

import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.donorExceptions.DonorDeleteException;
import com.project.bloodBank.donorExceptions.DonorFetchException;
import com.project.bloodBank.donorExceptions.DonorIdNotFoundException;
import com.project.bloodBank.donorExceptions.DonorSaveException;
import com.project.bloodBank.donorExceptions.DonorUpdateException;
import com.project.bloodBank.entity.DonorEntity;

public interface DonorService {

	DonorEntity saveDonor(DonorEntity donorEntity) throws DonorSaveException;

	DonorEntity getByDonorId(Long donorId) throws DonorIdNotFoundException;

	void updateDonor(DonorEntity donorEntity) throws DonorUpdateException;

	void deleteDonor(Long donorId) throws DonorDeleteException;

	List<DonorEntity> getAllDonors() throws DonorFetchException;

	DonorBean getByUserId(Long donorId) throws DonorIdNotFoundException;

	Long findDonorIdByUserId(Long userId) throws DonorIdNotFoundException;

}
