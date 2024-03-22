package com.patient.service;

import java.util.List;
import java.util.Optional;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.PatientRequestEntity;

public interface PatientRequestService {
	PatientRequestEntity savePatientRequest(PatientRequestEntity patientRequest);

	void deletePatientRequest(Long id);

	List<PatientRequestBean> getAllPatientRequest();

	Optional<PatientRequestEntity> updatePatientRequest(PatientRequestEntity patientRequest);

	PatientRequestBean getPatientRequest(Long id);

	void updateStatusAsAccepted(PatientRequestEntity requestEntity);

	void updateStatusAsRejected(PatientRequestEntity requestEntity);

	Long patientRequestsCount();
}
