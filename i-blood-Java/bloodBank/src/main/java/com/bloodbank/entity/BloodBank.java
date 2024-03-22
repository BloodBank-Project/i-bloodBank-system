package com.bloodbank.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="blood_bank")
public class BloodBank implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blood_bank_id")
	private long bloodBankId;
	
	@Column(name="blood_bank_name")
	private String bloodBankName;
	
	@Column(name="blood_bank_location")
	private String bloodBankLocation;
	
	@Column(name="availableBloodGroup")
	private String availableBloodGroup;
	
	@Column(name="blood_quantity")
	private int bloodQuantity;
	
}
