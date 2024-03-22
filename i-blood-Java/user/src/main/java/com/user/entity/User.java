package com.user.entity;

import java.sql.Date;

import com.user.constants.CommonConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_number")
	private Long contactNumber;

	@Column(name = "alternate_contact_number")
	private Long alternateContactNumber;

	@Column(name = "type")
	private String type = CommonConstants.role;

	@Column(name = "blood_group_id")
	private Long bloodGroupId;

	@Column(name = "blood_bank_id")
	private Long bloodBankId;

}
