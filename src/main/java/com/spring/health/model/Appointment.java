package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "appointments")
@Accessors(chain = true)
public class Appointment extends BaseClass {



  @DBRef
  @JsonIgnore
  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  @DBRef
  @JsonIgnore
  private Doctor doctor;



}
