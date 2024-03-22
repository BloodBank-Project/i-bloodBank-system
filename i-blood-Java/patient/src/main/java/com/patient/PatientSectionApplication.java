package com.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PatientSectionApplication  extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(PatientSectionApplication.class, args);
	}

}
