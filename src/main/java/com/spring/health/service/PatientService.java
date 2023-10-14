package com.spring.health.service;


import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Patient;
import org.bson.types.ObjectId;


import java.util.List;



public interface PatientService {

	PatientDto create(Patient patient)throws PatientException;
	PatientDto update (ObjectId id,PatientReqDto patientReqDto);
	PatientDto getById (ObjectId id);
	List<PatientDto> getAll();
	PatientDto delete (ObjectId id);

//	@Transactional
//	public void softDeleteById(Long id) {
//		Patient patient = patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//		patient.setStatus(Status.DELETE);
//		patientRepository.save(patient);
//	}

	}
