package com.spring.health.controller;

import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Patient;
import com.spring.health.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @PostMapping
    public PatientDto save(@RequestBody PatientDto patient) throws PatientException {
        return patientService.create(patient);
    }

    @PutMapping("/updatePatient")
    public PatientDto update(@RequestBody Patient patient,@RequestParam(required = false) String key)throws PatientException{
        return patientService.update(patient,key);
    }

    @GetMapping
    public PatientDto getByUuid(@RequestParam String key) throws PatientException {
        return patientService.getPatientByUuid(key);
    }

    @GetMapping("/email")
    public PatientDto searchPatient(@RequestParam("email") String email) throws PatientException {
       return patientService.searchPatient(email);
    }
}
