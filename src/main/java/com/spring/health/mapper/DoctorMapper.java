package com.spring.health.mapper;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    public DoctorDto toDto(Doctor doctor){
         DoctorDto doctorDto=CommonMapper.mapEntityToDto(doctor,DoctorDto.class);
         return doctorDto;
    }

    public Doctor toEntity(DoctorDto doctorDto){
        Doctor doctor=CommonMapper.mapDtoToEntity(doctorDto,Doctor.class);
        return doctor;
    }

    public Doctor toEntity(DoctorDto doctorDto,Doctor doctor){
        Doctor doctor1=CommonMapper.mapDtoToEntity(doctorDto,doctor);
        return doctor1;

    }

    public List<DoctorDto> toDtoList(List<Doctor> doctorList){
        return doctorList.stream().map(this::toDto).collect(Collectors.toList());
    }



}
