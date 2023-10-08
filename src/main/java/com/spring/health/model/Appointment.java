package com.spring.health.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "appointments")
@Accessors(chain = true)
public class Appointment extends BaseClass {


  private String patientId;
  private String patientName;
  private String patientNumber;
  private LocalDate date;
  private LocalTime time;

  private String complaint;
  private String doctorId;
  private String room;
  private String department;
  @DBRef
  private Patient patient;
  @DBRef
  private Doctor doctor;


}
