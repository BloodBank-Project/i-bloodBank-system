package com.user.bean;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBean {

	private Long userId;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Date dateOfBirth;

	private String gender;

	private String address;

	private Long contactNumber;

	private Long alternateContactNumber;

	private String type;

	private BloodBean bloodBean;

	private BloodBankBean bloodBankBean;
}
