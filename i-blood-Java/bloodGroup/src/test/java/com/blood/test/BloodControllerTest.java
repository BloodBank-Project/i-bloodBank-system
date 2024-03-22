package com.blood.test;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blood.controller.BloodController;
import com.blood.entity.Blood;
import com.blood.exception.NoDataFoundException;
import com.blood.service.BloodService;


@ExtendWith(MockitoExtension.class)
public class BloodControllerTest {

	@Mock
	private BloodService bloodService;

	@InjectMocks
	private BloodController bloodController;

	private Blood sampleBlood;

	@BeforeEach
	public void setUp() {
		sampleBlood = new Blood();
		sampleBlood.setBloodId(1);
		sampleBlood.setBloodType("A+");
	}

	@Test
	public void testSaveBlood() {
		ResponseEntity<Blood> response = bloodController.save(sampleBlood);
		verify(bloodService).saveBloodGroup(sampleBlood);
		assert(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void testGetById() throws NoDataFoundException {
		when(bloodService.getByBloodId(anyInt())).thenReturn(sampleBlood);

		ResponseEntity<Blood> response = bloodController.getById(1);
		verify(bloodService).getByBloodId(1);
		assert(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testGetAllBlood() throws NoDataFoundException {
		List<Blood> bloodList = new ArrayList<>();
		bloodList.add(sampleBlood);
		when(bloodService.getAllBloodGroups()).thenReturn(bloodList);

		ResponseEntity<List<Blood>> response = bloodController.getAll();
		verify(bloodService).getAllBloodGroups();
		assert(response.getStatusCode() == HttpStatus.OK);
	}
}