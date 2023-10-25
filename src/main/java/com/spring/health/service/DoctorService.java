package com.spring.health.service;


import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.TimeDateException;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface DoctorService {

    List<DoctorDto> findAllDoctors();
    Response saveDoctor(DoctorDto doctorDto);
    Response getDoctor( );
    Response getByIdDoctor(ObjectId id);
    Response updateDoctor(DoctorDto  doctorDto,ObjectId id);
    Response deleteDoctor(ObjectId id);
    Response loginDoctor(DoctorDto doctorDto);
    List<LocalDateTime> getDoctorAvailableTimingForBooking(String key, DoctorDto doctorDto) throws IOException, TimeDateException, DoctorException;

}
