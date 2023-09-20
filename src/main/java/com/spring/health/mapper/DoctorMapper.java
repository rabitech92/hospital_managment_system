package com.spring.health.mapper;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDto toDto(Doctor doctor){
         DoctorDto doctorDto=CommonMapper.mapDtoToEntity(doctor,DoctorDto.class);
         return doctorDto;
    }

}
