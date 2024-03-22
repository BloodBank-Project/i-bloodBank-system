package com.bloodbank.service;

import java.util.List;

import com.bloodbank.entity.BloodBank;

public interface BloodBankService {

	void saveBloodBank(BloodBank bloodbank);

	BloodBank getByBankId(Long bankId);

	void updateBloodBank(BloodBank bloodbank);

	void deleteBloodBank(Long id);

	List<BloodBank> getAllBloodBanks();
	
	public List<BloodBank> findByBloodBankLocation(String location);

}
