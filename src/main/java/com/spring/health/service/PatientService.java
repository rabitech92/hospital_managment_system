package com.spring.health.service;

import java.util.List;

import com.spring.health.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.health.model.Patient;
import com.spring.health.repository.PatientRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
				this.patientRepository = patientRepository;
	}

	public Patient save(Patient patient) {
			return patientRepository.save(patient);
	}
	
	public List<Patient> getAllPatient() {
				try {
					return patientRepository.findAll();
				} catch (Exception e) {
					{
						System.out.println("No patient save here");
					}
					return patientRepository.findAll();
				}
	}
	public Patient getByIdPatient(Long id) {
				return patientRepository.findById(id).get();
	}


	
	public Patient updatePatient(Patient patient,long id) {
				Patient patient2 =patientRepository.findById(id).get();
				patient2.setName(patient.getName());
				patient2.setAge(patient.getAge());
				patient2.setNid(patient.getNid());
				return patientRepository.save(patient2);
	}
	
	public void delete(Long id) {
				patientRepository.deleteById(id);
	}


	@Transactional
	public void softDeleteById(Long id) {
		Patient patient = patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		patient.setStatus(Status.DELETE);
		patientRepository.save(patient);
	}




//	public List<Patient> getAllNonDeletedPatients() {
//				return patientRepository.findByDeletedFalse();
//	}

	}
