package com.spring.health.service;


import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Patient;







public interface PatientService {

	PatientDto create(PatientReqDto patientReqDto)throws PatientException;
	PatientDto update (Patient user, String key)throws PatientException;
	PatientDto getPatientByUuid (String uuid)throws PatientException;
	PatientDto searchPatient(String email)throws PatientException;
	PatientDto deletePatient(String email)throws PatientException;

	}
