package com.spring.health.controller;

import java.util.ArrayList;
import java.util.List;

import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
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
	

//	@GetMapping("/non-deleted")
//	public ResponseEntity<List<Patient>> getNonDeletedPatients() {
//		List<Patient> nonDeletedPatients = patientService.getAllNonDeletedPatients();
//		return ResponseEntity.ok(nonDeletedPatients);
//	}

}
