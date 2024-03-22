package com.project.bloodBank.entity;

import java.io.Serializable;
import java.util.Date;

import com.project.bloodBank.constants.CommonConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "patient_request")
public class PatientRequestEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_request_id")
	private Long patientRequestId;

	@Column(name = "patient_request_units")
	private Long patientRequestUnits;

	@Column(name = "patient_request_on_date")
	private Date patientRequestOnDate;

	@Column(name = "patient_medical_condition")
	private String patientMedicalCondition;

	private Long patientId;

	@Column(nullable = false, columnDefinition = "varchar(255) default 'pending'")
	private String status;

	@PrePersist
	public void prePersist() {
		if (this.status == null) {
			this.status = CommonConstants.pending;
		}
	}

}
