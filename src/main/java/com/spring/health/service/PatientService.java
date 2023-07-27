package com.spring.health.service;

import java.util.List;
import java.util.Optional;

import com.spring.health.enums.ActiveEnum;
import com.spring.health.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.health.model.Patient;
import com.spring.health.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
				this.patientRepository = patientRepository;
	}

	public Patient save(Patient patient) {
				Doctor doctor =new Doctor();
				patient.setDoctor(doctor);
				return patientRepository.save(patient);
	}
	
	public List<Patient> getAllPatient() {
				try {
					return patientRepository.findAll();
				} catch (Exception e) {
					{
						System.out.println(e);
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
	public void softDeletePatient(Long id) {
				patientRepository.softDeleteById(id);
	}


//	public List<Patient> getAllNonDeletedPatients() {
//				return patientRepository.findByDeletedFalse();
//	}

	}
