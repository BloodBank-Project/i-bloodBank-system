package com.blood.service;

import java.util.List;

import com.blood.entity.Blood;

public interface BloodService {

	void saveBloodGroup(Blood blood);

	Blood getByBloodId(Integer bloodId);

	List<Blood> getAllBloodGroups();
}
