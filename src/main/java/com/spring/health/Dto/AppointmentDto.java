package com.spring.health.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {

  private String patientId;
  private String patientName;
  private String patientNumber;
  private String date;
  private String time;

  private  String complaint;
  private  String doctorId;
  private String room;
  private String department;
}
