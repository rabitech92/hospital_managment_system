package com.spring.health.Dto;

import com.spring.health.enums.Status;
import lombok.Data;


@Data
public class PatientDto {

    private String id;
    private String name;
    private int age ;
    private String email;
    private String nid;
    private Status status;
    private DoctorDto doctor;

}
