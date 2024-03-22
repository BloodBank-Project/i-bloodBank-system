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

	private long bloodBankId;

	private String bloodBankName;

	private String bloodBankLocation;

	private String availableBloodGroup;

	private int bloodQuantity;
}
