package com.spring.health.service;

import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.Response;
import org.bson.types.ObjectId;

public interface AppointmentService {

    Response saveAppointment(AppointmentDto appointmentDto);
    Response getApointment();
    Response getApointmentById(ObjectId id);
    Response updateApointment(AppointmentDto appointmentDto, ObjectId id);
    Response deleteApointment(ObjectId id);

}
