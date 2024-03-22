package com.patient.bean;

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
public class PatientRequestBean {

	private Long patientRequestId;
	private String bloodType;
	private String firstName;
	private Long patientRequestUnits;
	private Date patientRequestOnDate;
	private String patientMedicalCondition;
	private String status;
}
