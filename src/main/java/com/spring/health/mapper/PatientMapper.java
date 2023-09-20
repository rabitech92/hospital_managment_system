package com.spring.health.mapper;

import com.spring.health.Dto.PatientDto;
import com.spring.health.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient){
        PatientDto patientDto =CommonMapper.mapEntityToDto(patient,PatientDto.class);
        return patientDto;
    }

    public Patient toEntity(PatientDto patientDto){
        Patient patient= CommonMapper.mapDtoToEntity(patientDto,Patient.class);
        return  patient;
    }

}
