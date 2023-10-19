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



<<<<<<< HEAD
  @Id
  private ObjectId appointmentId;
  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  private Doctor doctor;
=======
  @DBRef
>>>>>>> parent of 607c5be (patient service impl get all doctors method)
  @JsonIgnore
  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  @DBRef
  @JsonIgnore
  private Doctor doctor;



}
