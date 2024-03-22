package com.project.bloodBank.testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.controller.DonorController;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.serviceImplementation.DonorServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class DonorControllerTest {

	@Mock
	private DonorServiceImplementation donorServiceImplementation;

	@InjectMocks
	private DonorController donorController;

	@Test
	public void testSaveDonorRecord_Success() {
		DonorEntity donorEntity = new DonorEntity(); // Create a mock DonorEntity
		when(donorServiceImplementation.saveDonor(donorEntity)).thenReturn(donorEntity);

		ResponseEntity<DonorEntity> responseEntity = donorController.saveDonorRecord(donorEntity);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(donorServiceImplementation, times(1)).saveDonor(donorEntity);
	}

	@Test
	public void testGetByDonorId_Success() {
		Long donorId = 1L;
		DonorEntity donorEntity = new DonorEntity(); // Create a mock DonorEntity
		when(donorServiceImplementation.getByDonorId(donorId)).thenReturn(donorEntity);

		ResponseEntity<DonorEntity> responseEntity = donorController.getByDonorId(donorId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(donorEntity, responseEntity.getBody());
		verify(donorServiceImplementation, times(1)).getByDonorId(donorId);
	}

	@Test
	public void testUpdateDonorRecord_Success() {
		DonorEntity donorEntity = new DonorEntity(); // Create a mock DonorEntity
		doNothing().when(donorServiceImplementation).updateDonor(donorEntity);

		ResponseEntity<DonorEntity> responseEntity = donorController.updateDonorRecord(donorEntity);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(donorServiceImplementation, times(1)).updateDonor(donorEntity);
	}

	@Test
	public void testDeleteDonorRecord_Success() {
		Long donorId = 1L;
		doNothing().when(donorServiceImplementation).deleteDonor(donorId);

		ResponseEntity<DonorEntity> responseEntity = donorController.deleteDonorRecord(donorId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(donorServiceImplementation, times(1)).deleteDonor(donorId);
	}

	@Test
	public void testDonorsList_Success() {
		List<DonorEntity> donorList = new ArrayList<>();
		when(donorServiceImplementation.getAllDonors()).thenReturn(donorList);

		ResponseEntity<List<DonorEntity>> responseEntity = donorController.donorsList();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(donorList, responseEntity.getBody());
		verify(donorServiceImplementation, times(1)).getAllDonors();
	}

	@Test
	public void testGetUserDetails_Success() {
		Long userId = 1L;
		DonorBean donorBean = new DonorBean(); // Create a mock DonorBean
		when(donorServiceImplementation.getByUserId(userId)).thenReturn(donorBean);

		ResponseEntity<DonorBean> responseEntity = donorController.getUserDetails(userId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(donorBean, responseEntity.getBody());
		verify(donorServiceImplementation, times(1)).getByUserId(userId);
	}

}
