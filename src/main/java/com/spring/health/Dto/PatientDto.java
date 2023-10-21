package com.spring.health.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import lombok.Data;


@Data
public class PatientDto {

    private String name;
    private String email;
    private String password;
    private int age ;
    private String gender;
    private String address;
    private String nid;
    private String type;
    @JsonIgnore
    private DoctorDto doctor;

}
