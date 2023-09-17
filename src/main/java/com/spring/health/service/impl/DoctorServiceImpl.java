package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors =doctorRepository.findAll();
        return doctors.stream().map((doctor) ->convertEntityToDto(doctor)).collect(Collectors.toList());
    }


    private DoctorDto convertEntityToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setName(doctor.getName());
        doctorDto.setPassword(doctor.getPassword());
        return doctorDto;
    }


}
