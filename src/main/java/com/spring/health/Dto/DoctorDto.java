package com.spring.health.Dto;

import lombok.Data;


@Data
public class DoctorDto {
    private String mobileNo;
    private String name;
    private String email;
    private String password;
    private String type;
    private String specialty;
    private Boolean insuranceAcceptance;
    private String education;
    private String experience;
}
