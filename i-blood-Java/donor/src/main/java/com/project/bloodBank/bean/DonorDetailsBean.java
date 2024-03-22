package com.project.bloodBank.bean;

import java.sql.Date;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonorDetailsBean {

	private Long donationId;

	private String bloodType;

	private String firstName;

	private Long bloodQuantity;

	private Date dateOfDonation;

	private String status;
}
