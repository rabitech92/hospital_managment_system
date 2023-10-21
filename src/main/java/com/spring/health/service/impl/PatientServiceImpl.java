package com.spring.health.service.impl;

import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.LoginException;
import com.spring.health.exception.PatientException;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.Patient;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@Transactional
public class PatientServiceImpl  implements PatientService {


    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final SessionRepository sessionRepository;


    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ModelMapper modelMapper, SessionRepository sessionRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.sessionRepository = sessionRepository;
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

    @Override
    public CurrentSession getCurrentUserByUuid(String uuid) throws LoginException {
        CurrentSession currentUserSession = sessionRepository.findByUuid(uuid);
        if(currentUserSession != null) {
            return currentUserSession;
        }else {
            throw new LoginException("Please enter valid key");
        }
    }
}
