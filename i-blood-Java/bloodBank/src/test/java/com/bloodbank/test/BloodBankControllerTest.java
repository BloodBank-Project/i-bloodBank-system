package com.bloodbank.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bloodbank.controller.BloodBankController;
import com.bloodbank.entity.BloodBank;
import com.bloodbank.exception.IdNotFoundException;
import com.bloodbank.service.BloodBankService;

@ExtendWith(MockitoExtension.class)
public class BloodBankControllerTest {

	@InjectMocks
	BloodBankController bloodBankController;

	@Mock
	BloodBankService bloodBankService;

	@Test
	public void saveBloodBankTest() {
		BloodBank bloodBank = new BloodBank();
		bloodBank.setBloodBankId(1L);
		bloodBank.setBloodBankName("Test Bank");

		ResponseEntity<BloodBank> responseEntity = bloodBankController.save(bloodBank);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		assertEquals(bloodBank, responseEntity.getBody());

		verify(bloodBankService, times(1)).saveBloodBank(bloodBank);
	}

	@Test
	public void getBloodBankByIdTest() throws IdNotFoundException {
		Long id = 1L;
		BloodBank bloodBank = new BloodBank();
		bloodBank.setBloodBankId(1L);
		when(bloodBankService.getByBankId(id)).thenReturn(bloodBank);

		ResponseEntity<BloodBank> responseEntity = bloodBankController.getById(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(bloodBank, responseEntity.getBody());
		verify(bloodBankService, times(1)).getByBankId(id);
	}

	@Test
	public void getAllBloodBanksTest() {
		List<BloodBank> bloodBanks = new ArrayList<>();
		BloodBank bloodBank1 = new BloodBank();
		BloodBank bloodBank2 = new BloodBank();
		bloodBanks.add(bloodBank1);
		bloodBanks.add(bloodBank2);
		when(bloodBankService.getAllBloodBanks()).thenReturn(bloodBanks);

		ResponseEntity<List<BloodBank>> responseEntity = bloodBankController.getAll();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(bloodBanks, responseEntity.getBody());
		verify(bloodBankService, times(1)).getAllBloodBanks();
	}

	@Test
	public void deleteBloodBankTest() throws IdNotFoundException {
		Long id = 1L;

		ResponseEntity<BloodBank> responseEntity = bloodBankController.delete(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(bloodBankService, times(1)).deleteBloodBank(id);
	}

	@Test
	public void updateBloodBankTest() throws IdNotFoundException {
		BloodBank bloodBank = new BloodBank();

		ResponseEntity<BloodBank> responseEntity = bloodBankController.update(bloodBank);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(bloodBankService, times(1)).updateBloodBank(bloodBank);
	}

	@Test
	public void findByLocationTest() throws IdNotFoundException {
		String location = "Test Location";
		List<BloodBank> bloodBanks = new ArrayList<>();
		when(bloodBankService.findByBloodBankLocation(location)).thenReturn(bloodBanks);

		ResponseEntity<List<BloodBank>> responseEntity = bloodBankController.findByLocation(location);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(bloodBanks, responseEntity.getBody());
		verify(bloodBankService, times(1)).findByBloodBankLocation(location);
	}
}