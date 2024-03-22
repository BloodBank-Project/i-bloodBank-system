package com.project.bloodBank.bean;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
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

	private Long bloodGroupId;

	private Long bloodBankId;

}
