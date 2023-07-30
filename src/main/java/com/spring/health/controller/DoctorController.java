package com.spring.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.health.model.Doctor;
import com.spring.health.service.DoctorService;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {
	
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public Doctor save(@RequestBody Doctor doctor) {
		return doctorService.save(doctor);
	}
	
	@GetMapping
	public List<Doctor> getAllDoctor() {
		return doctorService.getAllDoctor();
		
	}
	@GetMapping("/{id}")
	public Doctor getById(@PathVariable ("id") Long id) {
		return doctorService.getByIdDoctor(id);
	}
	
	@PutMapping("/{id}")
	public Doctor updatePatient(@RequestBody Doctor doctor,@PathVariable Long id) {
		return doctorService.updateDoctor(doctor, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		doctorService.delete(id);
		
	}
	
	
	

}
