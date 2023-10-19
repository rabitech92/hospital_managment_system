package com.spring.health.service;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.model.Doctor;

public interface AdminDoctorService {
    DoctorDto registerDoctor(Doctor doctor) throws DoctorException;

}
