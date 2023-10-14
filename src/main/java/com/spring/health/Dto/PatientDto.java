package com.spring.health.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import lombok.Data;


@Data
public class PatientDto {

    private String name;
    private String email;
    private int age ;
    private String gender;
    private String address;
    private String nid;
    private String type;
    private String password;
    @JsonIgnore
    private DoctorDto doctor;

}
