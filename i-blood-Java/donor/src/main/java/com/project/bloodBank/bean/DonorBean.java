package com.project.bloodBank.bean;

import java.util.List;

import com.project.bloodBank.entity.DonorDetailsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonorBean {

	private Long id;

	private UserBean userId;

	private List<DonorDetailsBean> donarDetails;

}
