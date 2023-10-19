package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.service.PatientService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl  implements PatientService {


    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;


    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto create(PatientReqDto patientReqDto) throws PatientException {
        return null;
    }

    @Override
    public PatientDto update(Patient user, String key) throws PatientException {
        return null;
    }

    @Override
    public PatientDto getPatientByUuid(String uuid) throws PatientException {
        return null;
    }

    @Override
    public PatientDto searchPatient(String email) throws PatientException {
        return null;
    }

    @Override
    public PatientDto deletePatient(String email) throws PatientException {
        return null;
    }
}
