package com.spring.health.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import com.spring.health.model.Appointment;
import com.spring.health.model.Doctor;
import com.spring.health.model.Message;
import com.spring.health.model.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


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
    private Doctor doctor;
    @JsonIgnore
    List<Appointment> listOfAppointments = new ArrayList<>();
    @JsonIgnore
    List<Review> listReviews = new ArrayList<>();
    @JsonIgnore
    List<Message> listOfMessage = new ArrayList<>();
}
