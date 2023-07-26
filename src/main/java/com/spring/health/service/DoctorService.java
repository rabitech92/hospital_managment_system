package com.spring.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
		
	}
	
	public List<Doctor> getAllDoctor( ) {
		return doctorRepository.findAll();
		
	}
	public Doctor getByIdDoctor(Long id ) {
		return doctorRepository.findById(id).get();
		
	}
	
	public Doctor updateDoctor(Doctor doctor, Long id ) {
		Doctor doctor2 = doctorRepository.findById(id).get();
		doctor2.setName(doctor.getName());
		doctor2.setEmail(doctor.getEmail());
		doctor2.setPassword(doctor.getPassword());
		return doctorRepository.save(doctor2);
		
	}
	
	public void delete(Long id) {
		doctorRepository.deleteById(id);
		
	}
	
//	public Doctor getByName(String...name) {
//		return doctorRepository.getByName(null)
//	}

}
