package com.project.bloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bloodBank.entity.DonorEntity;

public interface DonorRepository extends JpaRepository<DonorEntity, Long>{

	@Query("SELECT d.id FROM DonorEntity d WHERE d.userId = :userId")
    Long findIdByUserId(@Param("userId") Long userId);
}
