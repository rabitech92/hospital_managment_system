package com.spring.health.service;


import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.exception.*;
import com.spring.health.model.Appointment;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.Patient;
import jakarta.mail.MessagingException;

import java.io.IOException;


public interface PatientService {

	PatientDto create(PatientDto patientDto) throws PatientException;
	PatientDto update (Patient user, String key)throws PatientException;
	PatientDto getPatientByUuid (String uuid)throws PatientException;
	PatientDto searchPatient(String email)throws PatientException;
	PatientDto deletePatient(String email)throws PatientException;
	CurrentSession getCurrentUserByUuid(String uuid) throws LoginException;
	AppointmentDto bookAppointment(String key, Appointment appointment) throws AppointmentException, LoginException, DoctorException, IOException, TimeDateException, MessagingException;

	}
