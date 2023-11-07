package com.spring.health.Dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.model.Appointment;
import com.spring.health.model.Message;
import com.spring.health.model.Review;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class DoctorDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private String type;
    private String specialty;
    private Boolean insuranceAcceptance;
    private String education;
    private String experience;
    private int startDateCount;
    private int endtDateCount;
    private int duration;
    @JsonIgnore
    private List<Review> listOfReviews = new ArrayList<>();
    private String doctorImg;
    private Boolean validDoctor = true;
    @JsonIgnore
    List<Message> listOfMessage = new ArrayList<>();
    @JsonIgnore
    List<Appointment> listOfAppointments = new ArrayList<>();
    private Integer appointmentFromTime;
    private Integer appointmentToTime;
}
