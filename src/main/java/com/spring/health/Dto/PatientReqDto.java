package com.spring.health.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import com.spring.health.model.BaseClass;
import com.spring.health.model.Doctor;
import lombok.Data;

@Data
public class PatientReqDto extends BaseClass {

    private String name;
    private int age ;
    private String gender;
    private String address;
    private String email;
    private String password;
    private String nid;
    private String type;
    @JsonIgnore
    private Doctor doctor;




}
