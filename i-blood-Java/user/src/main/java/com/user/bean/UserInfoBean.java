package com.user.bean;

import java.sql.Date;

public interface UserInfoBean {
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

}
