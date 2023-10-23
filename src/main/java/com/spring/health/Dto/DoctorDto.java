package com.spring.health.Dto;

import lombok.Data;
import org.bson.types.ObjectId;


@Data
public class DoctorDto {
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private String type;
    private String specialty;
    private Boolean insuranceAcceptance;
    private String education;
    private String experience;

    private Integer appointmentFromTime;
    private Integer appointmentToTime;
}
