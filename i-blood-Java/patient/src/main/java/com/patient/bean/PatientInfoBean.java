package com.patient.bean;

import java.sql.Date;

public interface PatientInfoBean {
	Long getPatientId();

	Long getUserId();

	String getFirstName();

	String getLastName();

	String getEmai();

	String getPassword();

	Date getDateOfBirth();

	String getGender();

	String getAddress();

	Long getContactNumber();

	Long getAlternateContactNumber();

	String getType();

	Long getBloodGroupId();

	Long getBloodBankId();

}
