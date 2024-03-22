package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patient.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

	@Query("SELECT p.patientId FROM PatientEntity p WHERE p.userId = :userId")
	Long findIdByUserId(@Param("userId") Long userId);
}
