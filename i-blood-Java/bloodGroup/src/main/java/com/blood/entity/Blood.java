package com.blood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "blood_group")
public class Blood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blood_id")
	private Integer bloodId;

	@Column(name = "blood_type")
	private String bloodType;

}
