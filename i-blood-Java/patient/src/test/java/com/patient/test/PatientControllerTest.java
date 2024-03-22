package com.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.patient.bean.PatientBean;
import com.patient.controller.PatientController;
import com.patient.entity.PatientEntity;
import com.patient.patientExceptions.PatientDeleteException;
import com.patient.patientExceptions.PatientDetailsNotFoundException;
import com.patient.patientExceptions.PatientIdNotFoundException;
import com.patient.patientExceptions.PatientUpdateException;
import com.patient.service.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	@Mock
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;

	private PatientEntity testPatient;

	@BeforeEach
	public void setUp() {
		testPatient = new PatientEntity();
		testPatient.setPatientId(1L);
	}

	@Test
    public void testSavePatient() {
        when(patientService.savePatient(any(PatientEntity.class))).thenReturn(testPatient);
        ResponseEntity<PatientEntity> response = patientController.savePatient(testPatient);
        verify(patientService, times(1)).savePatient(any(PatientEntity.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

	@Test
	public void testDeletePatient() {

		doThrow(new PatientDeleteException("Patient not found")).when(patientService).deletePatient(anyLong());
		ResponseEntity<PatientEntity> response = patientController.deletePatient(1L);
		verify(patientService, times(1)).deletePatient(anyLong());

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetAllPatient_Success() {
		List<PatientEntity> patients = new ArrayList<>();
		patients.add(new PatientEntity());
		when(patientService.getAllPatients()).thenReturn(patients);

		ResponseEntity<List<PatientEntity>> response = patientController.getAllPatients();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(patients, response.getBody());
	}

	@Test
    public void testGetAllPatient() {
        
        when(patientService.getAllPatients()).thenThrow(PatientDetailsNotFoundException.class);

        ResponseEntity<List<PatientEntity>> response = patientController.getAllPatients();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertNull(response.getBody());
    }

	@Test
	public void testUpdatePatient_Success() {

		PatientEntity patient = new PatientEntity();
		patient.setPatientId(1L);
		Optional<PatientEntity> optionalPatient = Optional.of(patient);
		when(patientService.updatePatient(any(PatientEntity.class))).thenReturn(optionalPatient);

		ResponseEntity<PatientEntity> response = patientController.updatePatient(patient);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotEquals(patient, response.getBody());

	}

	@Test
	public void testUpdatePatient_PatientNotFoundException() {

		PatientEntity patient = new PatientEntity();
		patient.setPatientId(1L);
		when(patientService.updatePatient(any(PatientEntity.class))).thenThrow(PatientUpdateException.class);

		ResponseEntity<PatientEntity> response = patientController.updatePatient(patient);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	public void testGetUserDetails_Success() {

		Long patientId = 1L;
		PatientBean patientBean = new PatientBean();
		when(patientService.getByUserId(patientId)).thenReturn(patientBean);

		ResponseEntity<PatientBean> response = patientController.getUserDetails(patientId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(patientBean, response.getBody());
	}

	@Test
	public void testGetUserDetails() {

		Long patientId = 1L;
		when(patientService.getByUserId(patientId)).thenThrow(PatientIdNotFoundException.class);

		ResponseEntity<PatientBean> response = patientController.getUserDetails(patientId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}
}
