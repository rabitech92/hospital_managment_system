package com.spring.health.Dto;


import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {

  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  private Doctor doctor;

}
