package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
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
        DoctorDto doctorDto=modelMapper.map(doctor,DoctorDto.class);
        return doctorDto;
    }


}
