package com.spring.health.controller;

import java.util.ArrayList;
import java.util.List;

import com.spring.health.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.health.model.Patient;
import com.spring.health.service.PatientService;

@RestController
@RequestMapping("api")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/save")
	public Patient savepatient(@RequestBody Patient patient) {

		return patientService.save(patient);
	}
	
	@GetMapping("/getAll")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatient();
	}
	
	@GetMapping("/get/{id}")
	public Patient getBiId(@PathVariable ("id") Long id) {
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
		patientService.softDeletePatient(id);
		return ResponseEntity.ok("Patient with ID " + id + " has been soft deleted.");
	}
//	@GetMapping("/non-deleted")
//	public ResponseEntity<List<Patient>> getNonDeletedPatients() {
//		List<Patient> nonDeletedPatients = patientService.getAllNonDeletedPatients();
//		return ResponseEntity.ok(nonDeletedPatients);
//	}

}
