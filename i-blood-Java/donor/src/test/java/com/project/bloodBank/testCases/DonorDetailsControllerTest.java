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

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.controller.DonorDetailsController;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.serviceImplementation.DonorDetailsServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class DonorDetailsControllerTest {

	@Mock
	private DonorDetailsServiceImplementation detailsServiceImplementation;

	@InjectMocks
	private DonorDetailsController donorDetailsController;

	@Test
	public void testSaveDonation_Success() {
		DonorDetailsEntity donorEntity = new DonorDetailsEntity(); // Create a mock DonorDetailsEntity
		when(detailsServiceImplementation.saveDonation(donorEntity)).thenReturn(donorEntity);

		ResponseEntity<DonorDetailsEntity> responseEntity = donorDetailsController.saveDonation(donorEntity);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(detailsServiceImplementation, times(1)).saveDonation(donorEntity);
	}

	@Test
	public void testGetByDonationId_Success() {
		Long donationId = 1L;
		DonorDetailsBean donorDetailsBean = new DonorDetailsBean(); // Create a mock DonorDetailsBean
		when(detailsServiceImplementation.getByDonationId(donationId)).thenReturn(donorDetailsBean);

		ResponseEntity<DonorDetailsBean> responseEntity = donorDetailsController.getByDonationId(donationId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(donorDetailsBean, responseEntity.getBody());
		verify(detailsServiceImplementation, times(1)).getByDonationId(donationId);
	}

	@Test
	public void testUpdateDonorDetails_Success() {
		DonorDetailsEntity donorEntity = new DonorDetailsEntity(); // Create a mock DonorDetailsEntity
		doNothing().when(detailsServiceImplementation).updateDonation(donorEntity);

		ResponseEntity<DonorDetailsEntity> responseEntity = donorDetailsController.updateDonorDetails(donorEntity);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(detailsServiceImplementation, times(1)).updateDonation(donorEntity);
	}

	@Test
	public void testDeleteDonorDetails_Success() {
		Long donationId = 1L;
		doNothing().when(detailsServiceImplementation).deleteDonation(donationId);

		ResponseEntity<DonorDetailsEntity> responseEntity = donorDetailsController.deleteDonorDetails(donationId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(detailsServiceImplementation, times(1)).deleteDonation(donationId);
	}

	@Test
	public void testDonationsList_Success() {
		List<DonorDetailsBean> donationList = new ArrayList<>();
		when(detailsServiceImplementation.getAllDonations()).thenReturn(donationList);

		ResponseEntity<List<DonorDetailsBean>> responseEntity = donorDetailsController.donationsList();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(donationList, responseEntity.getBody());
		verify(detailsServiceImplementation, times(1)).getAllDonations();
	}

}
