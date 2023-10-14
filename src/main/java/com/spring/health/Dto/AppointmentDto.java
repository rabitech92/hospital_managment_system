package com.spring.health.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {

  private String id;
  @DBRef
  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  @DBRef
  private Doctor doctor;

}
