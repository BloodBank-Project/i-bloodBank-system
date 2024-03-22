package com.patient.service;

import java.util.List;
import java.util.Optional;

import com.patient.bean.PatientBean;
import com.patient.entity.PatientEntity;

public interface PatientService {
	PatientEntity savePatient(PatientEntity patient);

	PatientEntity getByPatientId(Long id);

	Optional<PatientEntity> updatePatient(PatientEntity patient);

	void deletePatient(Long id);

	List<PatientEntity> getAllPatients();
	
	PatientBean getByUserId(Long id);
	
	Long findPatientIdByUserId(Long userId);


}
