package com.spring.health.service;


import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor findByEmail(String email);
    List<DoctorDto> findAllDoctors();
	

}
