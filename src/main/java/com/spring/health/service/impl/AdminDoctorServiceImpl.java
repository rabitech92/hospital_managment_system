package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.mapper.DoctorMapper;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;


    @Override
    public DoctorDto registerDoctor(DoctorDto doctorDto) throws DoctorException {
        Doctor doctor1 = doctorRepository.findByEmail(doctorDto.getEmail());
        if (doctor1 == null) {
            doctorDto.setType("Doctor");
            doctorDto.setPassword(PatientServiceImpl.bCryptPasswordEncoder.encode(doctorDto.getPassword()));
            Doctor doctor = doctorMapper.toEntity(doctorDto);
            doctorRepository.save(doctor);
            DoctorDto doctorDto1 = doctorMapper.toDto(doctor);
            return doctorDto1;
        }
        throw new DoctorException("Doctor already register his name is. " + doctorDto.getName());
    }
}
