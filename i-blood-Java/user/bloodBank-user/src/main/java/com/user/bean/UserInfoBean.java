package com.user.bean;

import java.sql.Date;

public interface UserInfoBean {
	Long getUser_id();
	
	String getFirst_name();
	
	String getLast_name();

	String getEmail();
	
	String getPassword();
	
	Date getDate_of_birth();
	
	String getGender();
	
	String getAddress();
	
	Long getContact_number();
	
	Long getAlternate_contact_number();
	
	String getType();
	
	
}
