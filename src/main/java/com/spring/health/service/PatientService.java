package com.spring.health.service;


import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import org.bson.types.ObjectId;


import java.util.List;



public interface PatientService {

	PatientDto create(PatientReqDto patientReqDto);
	PatientDto update (PatientReqDto patientReqDto);
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
