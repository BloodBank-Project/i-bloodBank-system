package com.blood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.entity.Blood;

public interface BloodRepository extends JpaRepository<Blood, Integer> {

}