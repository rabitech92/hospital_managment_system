package com.spring.health.mapper;


import com.spring.health.Dto.AppointmentDto;
import com.spring.health.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {


  public AppointmentDto toDto(Appointment appointment) {
    AppointmentDto appointmentDto = CommonMapper.mapEntityToDto(appointment, AppointmentDto.class);
    return appointmentDto;
  }

  public Appointment toEntity(AppointmentDto appointmentDto) {
    Appointment appointment = CommonMapper.mapDtoToEntity(appointmentDto, Appointment.class);
    return appointment;

  }

  public Appointment toEntity(AppointmentDto appointmentDto, Appointment appointmentEntity) {
    Appointment appointment = CommonMapper.mapDtoToEntity(appointmentDto, appointmentEntity);
    return appointment;
  }

  public List<AppointmentDto> toDtoList(List<Appointment> appointmentList) {
    return appointmentList.stream().map(this::toDto).collect(Collectors.toList());
  }


}
