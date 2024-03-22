package com.project.bloodBank.entity;

import java.io.Serializable;
import java.sql.Date;

import com.project.bloodBank.constants.CommonConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "donor_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonorDetailsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donation_id")
	private Long donationId;

	@Column(name = "blood_quantity")
	private Long bloodQuantity;

	@Column(name = "date_of_donation")
	private Date dateOfDonation;

	@Column(name = "donor_id")
	private Long donorId;
	
	@Column(name = "status")
	private String status;

	@PrePersist
	public void prePersist() {
		if (this.status == null) {
			this.status = CommonConstants.pending;
		}
	}

}
