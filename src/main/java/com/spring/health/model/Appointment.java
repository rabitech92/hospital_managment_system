package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import lombok.*;
import lombok.experimental.Accessors;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "appointments")
@Accessors(chain = true)
public class Appointment{



  @Id
  private ObjectId appointmentId;
  private Patient patient;
  private LocalDateTime appointmentDateAndTime;
  private Doctor doctor;
  @JsonIgnore
  private Review review;
  @Override
  public int hashCode() {
    return Objects.hash(appointmentId);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Appointment other = (Appointment) obj;
    return Objects.equals(appointmentId, other.appointmentId);
  }



}
