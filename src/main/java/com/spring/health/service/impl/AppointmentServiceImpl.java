package com.spring.health.service.impl;

import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.Response;
import com.spring.health.mapper.AppointmentMapper;
import com.spring.health.model.Appointment;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.service.AppointmentService;
import com.spring.health.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Response saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment=appointmentMapper.toEntity(appointmentDto);
        appointmentRepository.save(appointment);
        if (appointment!=null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"Appointment done",appointmentMapper.toDto(appointment));
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
    }

    @Override
    public Response getApointment() {
        List<Appointment> appointmentList=appointmentRepository.findAll();
        if (appointmentList.isEmpty()){
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"There are no Appointment Data");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.FOUND,"Appointment has been retrieved",appointmentMapper.toDtoList(appointmentList),appointmentList.size());
    }

    @Override
    public Response getApointmentById(String id) {
        Appointment appointment=appointmentRepository.findById(id).orElse(null);
        if (appointment==null){
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Appointment not Found");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.FOUND,"Appoint Found",
                appointmentMapper.toDto(appointment));
    }

    @Override
    public Response updateApointment(AppointmentDto appointmentDto, String id) {
        Appointment existAppoint =appointmentRepository.findById(id).orElse(null);
        if (existAppoint==null){
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"Appoinment Not Found");
        }
        Appointment appointmentUpdate =appointmentMapper.toEntity(appointmentDto,existAppoint);
        appointmentUpdate=appointmentRepository.save(appointmentUpdate);
        return ResponseBuilder.getSuccessResponse(HttpStatus.ACCEPTED,"Appointment Updated",appointmentMapper.toDto(appointmentUpdate));

    }

    @Override
    public Response deleteApointment(String id) {
        if (!appointmentRepository.existsById(id)){
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"Appoint not found");
        }
        appointmentRepository.deleteById(id);
        return ResponseBuilder.getSuccessResponse(HttpStatus.NOT_FOUND,"Appoint Found Here", id);
    }
}
