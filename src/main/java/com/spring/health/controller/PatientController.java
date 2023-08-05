package com.spring.health.controller;

import java.util.ArrayList;
import java.util.List;

import com.spring.health.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.health.model.Patient;
import com.spring.health.service.PatientService;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public Patient savepatient(@RequestBody Patient patient) {
		return patientService.save(patient);
	}
	
	@GetMapping("/getAll")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatient();
	}
	
	@GetMapping("/{id}")
	public Patient getById(@PathVariable ("id") Long id) {
		return patientService.getByIdPatient(id);
	}
	
	@PutMapping("/update/{id}")
	public Patient updatePatient(@RequestBody Patient patient,@PathVariable Long id) {
		return patientService.updatePatient(patient, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		patientService.delete(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> softDeletePatient(@PathVariable Long id) {
		patientService.softDeleteById(id);
		return ResponseEntity.ok("Patient ID " + id + " has been successfully soft deleted.");
	}
//	@GetMapping("/non-deleted")
//	public ResponseEntity<List<Patient>> getNonDeletedPatients() {
//		List<Patient> nonDeletedPatients = patientService.getAllNonDeletedPatients();
//		return ResponseEntity.ok(nonDeletedPatients);
//	}

}
