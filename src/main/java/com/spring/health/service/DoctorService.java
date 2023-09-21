package com.spring.health.service;


import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.model.Doctor;
import org.bson.types.ObjectId;

import java.util.List;

public interface DoctorService {
    Doctor findByEmail(String email);
    List<DoctorDto> findAllDoctors();

    Response saveDoctor(DoctorDto doctorDto);
    Response getDoctor( );
    Response getByIdDoctor(ObjectId id);
    Response updateDoctor(DoctorDto  doctorDto,ObjectId id);
    Response deleteDoctor(ObjectId id);

	

}
