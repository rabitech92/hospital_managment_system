package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;
import com.spring.health.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Override
    public Doctor findByEmail(String email) {
        return null;
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        return null;
    }


}
