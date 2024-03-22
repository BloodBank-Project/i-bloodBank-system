package com.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.patient.bean.PatientRequestBean;
import com.patient.controller.PatientRequestController;
import com.patient.entity.PatientRequestEntity;
import com.patient.patientRequestExceptions.PatientRequestDeleteException;
import com.patient.service.PatientRequestService;


@ExtendWith(MockitoExtension.class)
public class PatientRequestControllerTest {

	@Mock
	private PatientRequestService patientRequestService;

	@InjectMocks
	private PatientRequestController patientRequestController;

	private PatientRequestEntity testRequest;

	@BeforeEach
	public void setUp() {

		testRequest = new PatientRequestEntity();
		testRequest.setPatientRequestId(1L);

	}

	@Test
    public void testSavePatientRequest() {
       
        when(patientRequestService.savePatientRequest(any(PatientRequestEntity.class))).thenReturn(testRequest);

        
        ResponseEntity<PatientRequestEntity> response = patientRequestController.savePatientRequest(testRequest);

       
        verify(patientRequestService, times(1)).savePatientRequest(any(PatientRequestEntity.class));

       
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

	@Test
	public void testDeletePatientRequest() {

		doThrow(new PatientRequestDeleteException("Request not found")).when(patientRequestService)
				.deletePatientRequest(anyLong());

		ResponseEntity<PatientRequestEntity> response = patientRequestController.deletePatientRequest(1L);

		verify(patientRequestService, times(1)).deletePatientRequest(anyLong());

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetAllPatientRequest() {

		List<PatientRequestBean> testList = new ArrayList<>();

		when(patientRequestService.getAllPatientRequest()).thenReturn(testList);

		ResponseEntity<List<PatientRequestBean>> response = patientRequestController.getAllPatientRequest();

		verify(patientRequestService, times(1)).getAllPatientRequest();

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetPatientRequest() {

		PatientRequestBean testBean = new PatientRequestBean();

		when(patientRequestService.getPatientRequest(anyLong())).thenReturn(testBean);

		ResponseEntity<PatientRequestBean> response = patientRequestController.getPatientRequest(1L);

		verify(patientRequestService, times(1)).getPatientRequest(anyLong());

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
    public void testUpdatePatientRequest() {
   
        when(patientRequestService.updatePatientRequest(any(PatientRequestEntity.class))).thenReturn(Optional.of(testRequest));
    
        ResponseEntity<Optional<PatientRequestEntity>> response = patientRequestController.updatePatientRequest(testRequest);
       
        verify(patientRequestService, times(1)).updatePatientRequest(any(PatientRequestEntity.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
