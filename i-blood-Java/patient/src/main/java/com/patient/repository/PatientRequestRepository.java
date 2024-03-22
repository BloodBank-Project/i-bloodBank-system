package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.PatientRequestEntity;

import jakarta.transaction.Transactional;

public interface PatientRequestRepository extends JpaRepository<PatientRequestEntity, Long> {

	@Query("SELECT new com.patient.bean.PatientRequestBean(A.patientRequestId, D.bloodType,C.firstName, A.patientRequestUnits,"
			+ "A.patientRequestOnDate, A.patientMedicalCondition,A.status) " + "FROM PatientRequestEntity A "
			+ "JOIN PatientEntity B ON A.patientId = B.patientId " + "JOIN UserEntity C ON C.userId = B.userId "
			+ "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId " + "WHERE A.patientRequestId = :patientId")

	PatientRequestBean getBloodGroup(@Param("patientId") Long patientId);

	@Query(value = "SELECT C.firstName,D.bloodType" + " FROM PatientRequestEntity A "
			+ "JOIN PatientEntity B ON A.patientId = B.patientId " + "JOIN UserEntity C ON C.userId = B.userId "
			+ "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId where A.patientRequestId =:patientId")
	String getBloodGroupType(@Param("patientId") Long patientId);

	@Modifying
	@Transactional
	@Query("UPDATE BloodBankEntity bb SET bb.bloodQuantity = bb.bloodQuantity - :bloodQuantity "
			+ "WHERE bb.bloodBankId = (" + "SELECT u.bloodBankId " + "FROM UserEntity u "
			+ "JOIN PatientEntity d ON u.userId = d.userId "
			+ "JOIN PatientRequestEntity dd ON d.patientId = dd.patientId "
			+ "WHERE dd.patientRequestId = :patientRequestId" + ")")
	void updateBloodQuantity(Long patientRequestId, Long bloodQuantity);

	@Query("SELECT COUNT(patientRequestId) FROM PatientRequestEntity")
	Long countpatientRequets();
}
