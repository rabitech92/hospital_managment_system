package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.mapper.CommonMapper;
import com.spring.health.mapper.DoctorMapper;
import com.spring.health.model.Doctor;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorMapper doctorMapper;


    @Override
    public DoctorDto registerDoctor(Doctor doctor) throws DoctorException {
        LoginDto doctor1=doctorRepository.findByEmail(doctor.getEmail());
        if (doctor1!=null){
            doctor.setType("Doctor");
            doctor.setPassword(PatientServiceImpl.bCryptPasswordEncoder.encode(doctor.getPassword()));
            doctorRepository.save(doctor);
            DoctorDto doctorDto= doctorMapper.toDto(doctor);
            return doctorDto;
        }
        throw new DoctorException("Doctor already register with is mobile no. " +doctor.getEmail());
    }
}
