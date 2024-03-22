package com.project.bloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.entity.DonorDetailsEntity;

import jakarta.transaction.Transactional;

public interface DonorDetailsRepository extends JpaRepository<DonorDetailsEntity, Long> {

	@Query("SELECT new com.project.bloodBank.bean.DonorDetailsBean(A.donationId, D.bloodType,C.firstName, A.bloodQuantity, A.dateOfDonation, A.status) "
			+ "FROM DonorDetailsEntity A " + "JOIN DonorEntity B ON A.donorId = B.id "
			+ "JOIN UserEntity C ON C.userId = B.userId " + "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId "
			+ "WHERE A.donationId = :donorId")
	DonorDetailsBean getBloodGroup(@Param("donorId") Long donorId);

	@Query("SELECT C.firstName,D.bloodType" + " FROM DonorDetailsEntity A " + "JOIN DonorEntity B ON A.donorId = B.id "
			+ "JOIN UserEntity C ON C.userId = B.userId "
			+ "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId where A.donationId =:donorId")
	String getBloodGroupType(@Param("donorId") Long donorId);

	@Modifying
	@Transactional
	@Query("UPDATE BloodBankEntity bb SET bb.bloodQuantity = bb.bloodQuantity + :bloodQuantity "
			+ "WHERE bb.bloodBankId = (" + "SELECT u.bloodBankId " + "FROM UserEntity u "
			+ "JOIN DonorEntity d ON u.userId = d.userId " + "JOIN DonorDetailsEntity dd ON d.id = dd.donorId "
			+ "WHERE dd.donationId = :donationId" + ")")
	void updateBloodQuantity(Long donationId, Long bloodQuantity);

	@Query("SELECT COUNT(donationId) FROM DonorDetailsEntity")
	Long countDonations();

	@Query("SELECT SUM(totalCount) FROM ( "
			+ "SELECT COUNT(*) AS totalCount FROM PatientRequestEntity WHERE status = 'Accepted' " + "UNION ALL "
			+ "SELECT COUNT(*) AS totalCount FROM DonorDetailsEntity WHERE status = 'Accepted' "
			+ ") AS combinedCounts")
	Long getTotalAcceptedCount();

	@Query("SELECT SUM(totalCount) FROM ( "
			+ "SELECT COUNT(*) AS totalCount FROM PatientRequestEntity WHERE status = 'Rejected' " + "UNION ALL "
			+ "SELECT COUNT(*) AS totalCount FROM DonorDetailsEntity WHERE status = 'Rejected' "
			+ ") AS combinedCounts")
	Long getTotalRejectedCount();

	@Query("SELECT SUM(totalCount) FROM ( "
			+ "SELECT COUNT(*) AS totalCount FROM PatientRequestEntity WHERE status = 'Pending' " + "UNION ALL "
			+ "SELECT COUNT(*) AS totalCount FROM DonorDetailsEntity WHERE status = 'Pending' " + ") AS combinedCounts")
	Long getTotalPendingCount();
	
	
	@Query("SELECT COUNT(bloodBankId) FROM BloodBankEntity")
	Long getBloodBankCount();
}
