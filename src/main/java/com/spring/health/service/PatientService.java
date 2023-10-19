package com.spring.health.service;


import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Patient;
import org.bson.types.ObjectId;


import java.util.List;



public interface PatientService {

	PatientDto create(Patient patient)throws PatientException;
<<<<<<< HEAD
	PatientDto update (Patient user, String key)throws PatientException;
	PatientDto getPatientByUuid (String uuid)throws PatientException;
	List<PatientDto> getAll()throws PatientException;
	PatientDto delete (ObjectId id)throws PatientException;
	CurrentSession getCurrentPatientByUuid(String uuid) throws LoginException;
	AppointmentDto bookAppointment(String key, Appointment appointment) throws AppointmentException, LoginException, DoctorException, IOException, TimeDateException, MessagingException;
	List<Appointment> getAllAppointmenPatientWise(String key)throws AppointmentException, PatientException;
	Appointment updateAppointment(String key, Appointment newAppointment) throws AppointmentException, PatientException, DoctorException, IOException, TimeDateException;
	List<Doctor> getAllDoctors() throws DoctorException;
	PatientDto searchPatient(String email)throws PatientException;



=======
	PatientDto update (ObjectId id,PatientReqDto patientReqDto);
	PatientDto getById (ObjectId id);
	List<PatientDto> getAll();
	PatientDto delete (ObjectId id);
>>>>>>> parent of 607c5be (patient service impl get all doctors method)

//	@Transactional
//	public void softDeleteById(Long id) {
//		Patient patient = patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//		patient.setStatus(Status.DELETE);
//		patientRepository.save(patient);
//	}

	}
