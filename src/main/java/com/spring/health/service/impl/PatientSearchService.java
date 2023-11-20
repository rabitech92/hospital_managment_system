package com.spring.health.service.impl;

import com.spring.health.model.Patient;
import com.spring.health.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientSearchService {

    private PatientRepository patientRepository;

    public List<Patient> searchPatient(String name){
        return patientRepository.findPatientByNameContains(name);
    }
}
