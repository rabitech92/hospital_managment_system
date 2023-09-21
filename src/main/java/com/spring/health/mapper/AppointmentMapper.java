package com.spring.health.mapper;


import com.spring.health.Dto.AppointmentDto;
import com.spring.health.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

  //ModelMapper modelMapper = new ModelMapper();
  public AppointmentDto toDto(Appointment appointment) {
    AppointmentDto appointmentDto = CommonMapper.mapEntityToDto(appointment, AppointmentDto.class);
    if (appointmentDto != null) {
      if (appointment.getDate() != null) {
        appointmentDto.setDate(
            appointment.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      }
      if (appointment.getTime() != null) {
        appointmentDto.setTime(appointmentDto.getTime().toString());
      }

    }
    return appointmentDto;
  }

  public Appointment toEntity(AppointmentDto appointmentDto) {
    Appointment appointment = CommonMapper.mapDtoToEntity(appointmentDto, Appointment.class);
    if (appointment != null) {
      if (appointmentDto.getDate() != null) {
        appointment.setDate(
            LocalDate.parse(appointmentDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      }
      if (appointmentDto.getTime() != null) {
        appointment.setTime(
            LocalTime.parse(appointmentDto.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss")));
      }
    }
    return appointment;

  }

  public Appointment toEntity(AppointmentDto appointmentDto, Appointment appointmentEntity) {
    Appointment appointment = CommonMapper.mapDtoToEntity(appointmentDto, appointmentEntity);
    if (appointment != null) {
      if (appointmentDto.getDate() != null) {
        appointment.setDate(
            LocalDate.parse(appointmentDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      }
      if (appointmentDto.getTime() != null) {
        appointment.setTime(
            LocalTime.parse(appointmentDto.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss")));
      }
    }
    return appointment;
  }

  public List<AppointmentDto> toDtoList(List<Appointment> appointmentList) {
    return appointmentList.stream().map(this::toDto).collect(Collectors.toList());
  }


}
