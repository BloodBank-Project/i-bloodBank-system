package com.user.bean;


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
public class BloodBankBean {

	private long blood_bank_id;

	private String blood_bank_name;

	private String blood_bank_location;

	private String available_blood_group;

	private int blood_quantity;
}
