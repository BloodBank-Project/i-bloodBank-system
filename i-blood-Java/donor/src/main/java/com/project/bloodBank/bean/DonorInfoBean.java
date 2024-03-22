package com.project.bloodBank.bean;

import java.sql.Date;

public interface DonorInfoBean {

	Long getId();

	Long getUserId();

	String getFirstName();

	String getLastName();

	String getEmail();

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
