package com.spring.health.service;

import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.Response;

public interface AppointmentService {

    Response saveAppointment(AppointmentDto appointmentDto);
    Response getApointment();
    Response getApointmentById(String id);
    Response updateApointment(AppointmentDto appointmentDto,String id);
    Response deleteApointment(String id);

}
