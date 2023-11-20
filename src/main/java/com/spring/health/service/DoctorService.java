package com.spring.health.service;


import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.model.Doctor;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto) throws DoctorException;
    List<DoctorDto> findAllDoctors()throws DoctorException;
    DoctorDto getDoctorById(ObjectId id)throws DoctorException;
    void deleteDoctor(ObjectId id) throws DoctorException;
    DoctorDto updateAndSaveDoctor(DoctorDto doctorDto,ObjectId id)throws DoctorException;
    DoctorDto saveFile(DoctorDto doctorDto, MultipartFile file,String docName);
    Response loginDoctor(DoctorDto doctorDto);


}
