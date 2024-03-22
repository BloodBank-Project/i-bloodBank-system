package com.bloodbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bloodbank.entity.BloodBank;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

	@Query(value = "SELECT * FROM Blood_Bank WHERE blood_bank_location LIKE %:location% ", nativeQuery = true)
	List<BloodBank> findByLocation(String location);
	
}
