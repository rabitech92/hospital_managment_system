package com.spring.health.Dto;

import com.spring.health.enums.Status;
import com.spring.health.model.BaseClass;
import com.spring.health.model.Doctor;
import lombok.Data;

@Data
public class PatientReqDto extends BaseClass {

    private String name;
    private int age ;
    private String email;
    private String nid;
    private Status status;
    private Doctor doctor;


}
